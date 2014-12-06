package net.dasherz.wifiwolf.wifidog.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.dasherz.wifiwolf.domain.Connection;
import net.dasherz.wifiwolf.domain.Token;
import net.dasherz.wifiwolf.service.ConnectionService;
import net.dasherz.wifiwolf.service.TokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/portal")
public class PortalController {

	private static final Logger logger = LoggerFactory
			.getLogger(PortalController.class);

	@Inject
	private TokenService tokenService;

	@Inject
	private ConnectionService connectionService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String portal(@RequestParam("gw_id") String gatewayId, Model model,
			HttpSession session) {
		logger.debug("gateway id: " + gatewayId);
		model.addAttribute("wifidogHost", session.getAttribute("wifidogHost"));
		model.addAttribute("wifidogPort", session.getAttribute("wifidogPort"));
		model.addAttribute("token",
				tokenService.getToken((Long) session.getAttribute("tokenId"))
						.getToken());
		return "portal";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request,
			HttpServletResponse response, String token, String wifidogHost,
			String wifidogPort) throws IOException {
		logger.debug("User is logging out: " + token);
		Token result = tokenService.findByToken(token);
		if (result != null) {
			Connection connection = result.getConnection();
			connection.setStatus(2);
			connectionService.save(connection);
		}
		response.sendRedirect("http://" + wifidogHost + ":" + wifidogPort
				+ "/wifidog/auth?logout=1&token=" + token);
	}
}
