package net.dasherz.wifiwolf.user.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.dasherz.wifiwolf.domain.Token;
import net.dasherz.wifiwolf.service.AuthTypeService;
import net.dasherz.wifiwolf.service.NodeService;
import net.dasherz.wifiwolf.service.PhoneUserService;
import net.dasherz.wifiwolf.service.TokenService;
import net.dasherz.wifiwolf.service.UserService;

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

	@Inject
	private NodeService nodeService;

	@Inject
	private AuthTypeService authTypeService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String userName, String userPassword, String phoneNum,
			String phoneCode, String wifidogHost, String wifidogPort,
			String authType, String gw_id, Model model) throws IOException {
		if (userService.validateUser(userName, userPassword, phoneNum,
				phoneCode, authType)) {
			Token token = tokenService.createToken(
					authTypeService.getEnabledAuthType(),
					userService.findUserByUsername(userName),
					phoneUserService.findByPhoneNum(phoneNum),
					nodeService.findByGatewayId(gw_id));
			return "redirect:http://" + wifidogHost + ":" + wifidogPort
					+ "/wifidog/auth?token=" + token.getToken();
		}
		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		model.addAttribute("gw_id", gw_id);
		model.addAttribute("authType", authType);
		return "/wifi/login";
	}

	@RequestMapping(value = "/phoneVerify")
	public void phoneVerify(HttpServletRequest request) {
		String phoneNum = request.getParameter("phoneNum");
		phoneUserService.sendPhoneMessage(phoneNum);
	}
}
