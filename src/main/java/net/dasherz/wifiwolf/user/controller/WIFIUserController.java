package net.dasherz.wifiwolf.user.controller;

import java.io.IOException;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.domain.Token;
import net.dasherz.wifiwolf.service.PhoneUserService;
import net.dasherz.wifiwolf.service.TokenService;
import net.dasherz.wifiwolf.service.UserService;

import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/wifi")
public class WIFIUserController {
	@Inject
	private UserService userService;

	@Inject
	private PhoneUserService phoneUserService;

	@Inject
	private TokenService tokenService;

	@RequestMapping(value = "/login")
	public String login(String userName, String userPassword, String phoneNum,
			String phoneCode, String wifidogHost, String wifidogPort,
			AuthType authType, Node node, Session session, Model model)
			throws IOException {
		if (userService.validateUser(userName, userPassword, phoneNum,
				phoneCode, authType)) {
			session.setAttribute("userId", userName);
			Token token = tokenService.createToken(authType,
					userService.findUserByUsername(userName),
					phoneUserService.findByPhoneNum(phoneNum), node);
			return "redirect:http://" + wifidogHost + ":" + wifidogPort
					+ "/wifidog/auth?token=" + token.getToken();
		}
		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		model.addAttribute("node", node);
		return "/wifi/login";
	}

	@RequestMapping(value = "/phoneVerify", method = RequestMethod.POST)
	public String phoneVerify(String phoneNum) {
		phoneUserService.sendPhoneMessage(phoneNum);
		return "/wifi/login";
	}
}
