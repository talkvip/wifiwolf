package net.dasherz.wifiwolf.wifidog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.dasherz.wifiwolf.wifidog.constant.UserList;

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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String portal(@RequestParam("gw_id") String gatewayId, Model model,
			HttpSession session) {
		logger.debug("gateway id: " + gatewayId);
		model.addAttribute("userId", session.getAttribute("userId"));
		return "portal";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, String userId) {
		UserList.removeUser(userId);
		logger.debug("User is logging out: " + userId);
		return "login";
	}

}
