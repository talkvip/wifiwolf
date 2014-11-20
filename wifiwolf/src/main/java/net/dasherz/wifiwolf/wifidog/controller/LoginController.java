package net.dasherz.wifiwolf.wifidog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String login(String gw_id, String gw_address, String gw_port,
			String url, Model model) {
		logger.debug("gateway id: " + gw_id);
		logger.debug("gateway address: " + gw_address);
		logger.debug("gateway port: " + gw_port);
		logger.debug("gateway url: " + url);
		model.addAttribute("wifidogHost", gw_address);
		model.addAttribute("wifidogPort", gw_port);
		return "login";
	}
}
