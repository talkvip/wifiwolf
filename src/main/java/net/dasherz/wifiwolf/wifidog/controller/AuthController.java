package net.dasherz.wifiwolf.wifidog.controller;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.dasherz.wifiwolf.common.util.Constants;
import net.dasherz.wifiwolf.common.util.DateUtil;
import net.dasherz.wifiwolf.domain.Connection;
import net.dasherz.wifiwolf.domain.Token;
import net.dasherz.wifiwolf.service.ConnectionService;
import net.dasherz.wifiwolf.service.TokenService;
import net.dasherz.wifiwolf.wifidog.constant.AuthStage;
import net.dasherz.wifiwolf.wifidog.constant.WifidogConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
	private static final String AUTH_RESPONSE_PREFIX = "Auth: ";
	private static final Logger logger = LoggerFactory
			.getLogger(AuthController.class);

	@Inject
	TokenService tokenService;

	@Inject
	ConnectionService connectionService;

	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	public void doAuth(@RequestParam String stage, @RequestParam String ip,
			@RequestParam String mac, @RequestParam String token,
			@RequestParam long incoming, @RequestParam long outgoing,
			HttpServletResponse response, HttpSession session)
			throws IOException {
		logger.debug("stage: " + stage);
		logger.debug("user ip: " + ip);
		logger.debug("user mac: " + mac);
		logger.debug("user token: " + token);
		logger.debug("incoming: " + incoming);
		logger.debug("outgoing: " + outgoing);
		Token currentToken = tokenService.findByToken(token);
		AuthStage currentStage = AuthStage.getAuthStage(stage);

		switch (currentStage) {
		case LOGIN:
			boolean isTokenValid = validateToken(currentToken);
			if (isTokenValid) {
				connectionService.createConnection(ip, mac,
						tokenService.findByToken(token), incoming, outgoing);
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_ALLOWED);
			} else {
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_DENIED);
			}

			break;
		case COUNTERS:
			Connection connection = currentToken.getConnection();
			if (isUserOnline(connection)) {
				connection.setIncoming(incoming);
				connection.setOutgoing(outgoing);
				connection.setUpdateTime(new Date());
				connectionService.save(connection);
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_ALLOWED);
			} else {
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_DENIED);
			}
			break;
		case LOGOUT:
			Connection toBeKilled = currentToken.getConnection();
			toBeKilled.setStatus(Constants.STATUS_CONNECTION_CLOSED);
			connectionService.save(toBeKilled);
			break;
		default:
			logger.warn(
					"Unknown request: [stage={}, ip={}, mac={},token={},incoming={},outgoing={}]",
					stage, ip, mac, token, incoming, outgoing);
			break;
		}
	}

	private boolean validateToken(Token currentToken) {
		if (currentToken == null
				|| !currentToken.getStatus().equals(
						Constants.STATUS_TOKEN_NORMAL)) {
			return false;
		} else if (currentToken.getConnection() != null) {
			return false;
		} else {

			return true;
		}
	}

	private boolean isUserOnline(Connection connection) {
		if (connection == null) {
			return false;
		}
		// TODO: for further, we can add wifi restrictions here.

		if (connection.getStatus().equals(Constants.STATUS_CONNECTION_CLOSED)) {
			return false;
		}

		if (connection.getStatus().equals(Constants.STATUS_CONNECTION_NORMAL)) {
			Date lastUpdate = connection.getUpdateTime();
			// if last update time is 2 minutes ago, then close the connection
			if (DateUtil.getMinutesPasted(lastUpdate) > WifidogConstants.CONNECTION_TIMEOUT_MINUTE) {
				return false;
			}

			return true;
		}
		return false;

	}

}
