package net.dasherz.wifiwolf.wifidog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.dasherz.wifiwolf.wifidog.constant.UserList;
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
				UserList.addUser(UserList.getTokens().get(token));
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_ALLOWED);
			} else {
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_DENIED);
			}
		} else {
			if (isUserOnline(token)) {
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_ALLOWED);
			} else {
				response.getWriter().write(
						AUTH_RESPONSE_PREFIX + WifidogConstants.AUTH_DENIED);
			}
		}
	}

	private boolean isUserOnline(String token) {
		// TODO Auto-generated method stub

		return UserList.getUsers().contains(UserList.getTokens().get(token));
	}

	private boolean validateToken(String token) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean isLogin(String stage) {
		if (stage.equalsIgnoreCase(WifidogConstants.STAGE_LOGIN)) {
			return true;
		} else {
			return false;
		}
	}

}