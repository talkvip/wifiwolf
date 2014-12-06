package net.dasherz.wifiwolf.user.controller;

import java.io.IOException;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.AuthType;
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
import org.springframework.web.bind.annotation.RequestParam;

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
	public String login(String username, String userPassword, String phoneNum,
			String phoneCode, String wifidogHost, String wifidogPort,
			String gw_id, Model model) throws IOException {
		// TODO: validateUser() method should return a code to identify the
		// errors.
		AuthType authType = authTypeService.getEnabledAuthType();
		if (userService.validateUser(username, userPassword, phoneNum,
				phoneCode, authType.getAuthType())) {
			Token token = tokenService.createToken(
					authType,
					userService.findUserByUsername(username),
					// TODO phone number should not be unique, for recording
					// user behaver
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
	public void phoneVerify(
			@RequestParam(value = "phoneNum", required = true) String phoneNum) {
		phoneUserService.sendPhoneMessage(phoneNum);
	}
}
