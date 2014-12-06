package net.dasherz.wifiwolf.wifidog.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import net.dasherz.wifiwolf.service.ConnectionService;
import net.dasherz.wifiwolf.service.TokenService;
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
			HttpServletResponse response) throws IOException {
		logger.debug("stage: " + stage);
		logger.debug("user ip: " + ip);
		logger.debug("user mac: " + mac);
		logger.debug("user token: " + token);
		logger.debug("incoming: " + incoming);
		logger.debug("outgoing: " + outgoing);

		if (isLogin(stage)) {
			boolean isValid = validateToken(token);
			if (isValid) {
				// TODO store origin url
				connectionService.createConnection(ip, mac,
						tokenService.findByToken(token), incoming, outgoing);
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_ALLOWED);
			} else {
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_DENIED);
			}
		} else {
			if (isUserOnline(token)) {
				// TODO: update the current connection.
				// TODO: for further, we can add wifi restrictions here.
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_ALLOWED);
			} else {
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_DENIED);
			}
		}
	}

	private boolean isUserOnline(String token) {
		return tokenService.isUserOnline(token);
	}

	private boolean validateToken(String token) {
		return tokenService.validateToken(token);
	}

	private boolean isLogin(String stage) {
		if (stage.equalsIgnoreCase(WifidogConstants.STAGE_LOGIN)) {
			return true;
		} else {
			return false;
		}
	}

}
