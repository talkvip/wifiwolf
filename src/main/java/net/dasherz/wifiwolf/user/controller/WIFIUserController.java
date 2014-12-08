package net.dasherz.wifiwolf.user.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String wifidogHost, String wifidogPort, String gw_id,
			String authType, String registerType, Model model)
			throws IOException {
		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		model.addAttribute("gw_id", gw_id);
		model.addAttribute("authType", authType);
		model.addAttribute("registerType", registerType);
		return "/wifi/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String userPassword, String phoneNum,
			String phoneCode, String wifidogHost, String wifidogPort,
			String gw_id, Model model, HttpSession session) throws IOException {
		// TODO: validateUser() method should return a code to identify the
		// errors.
		AuthType authType = authTypeService.getEnabledAuthType();
		if (userService.validateUser(username, userPassword, phoneNum,
				phoneCode, authType.getAuthType())) {
			// TODO for PHONE type, store phone number
			Token token = tokenService.createToken(
					authType,
					userService.findUserByUsername(username),
					// TODO phone number should not be unique, for recording
					// user behaver
					phoneUserService.findByPhoneNum(phoneNum),
					nodeService.findByGatewayId(gw_id));
			session.setAttribute("tokenId", token.getId());
			session.setAttribute("wifidogHost", wifidogHost);
			session.setAttribute("wifidogPort", wifidogPort);
			return "redirect:http://" + wifidogHost + ":" + wifidogPort
					+ "/wifidog/auth?token=" + token.getToken();
		}
		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		model.addAttribute("gw_id", gw_id);
		model.addAttribute("authType", authType.getAuthType());
		model.addAttribute("registerType", authType.getRegisterType());
		return "/wifi/login";
	}

	@RequestMapping(value = "/phoneVerify")
	public void phoneVerify(
			@RequestParam(value = "phoneNum", required = true) String phoneNum,
			HttpServletResponse response) throws IOException {
		phoneUserService.sendPhoneMessage(phoneNum);
		response.getWriter().write("code sent");
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(String wifidogHost, String wifidogPort,
			String gw_id, String authType, String registerType, Model model) {
		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		model.addAttribute("gw_id", gw_id);
		model.addAttribute("authType", authType);
		model.addAttribute("registerType", registerType);
		return "/wifi/resetPassword";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(String username, String wifidogHost,
			String wifidogPort, String gw_id, String authType,
			String registerType, Model model) {
		boolean result = userService.resetPassword(username);
		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		model.addAttribute("gw_id", gw_id);
		model.addAttribute("authType", authType);
		model.addAttribute("result", result);
		model.addAttribute("registerType", registerType);
		return "/wifi/resetPassword";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(String wifidogHost, String wifidogPort,
			String gw_id, String authType, String registerType, Model model) {
		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		model.addAttribute("gw_id", gw_id);
		model.addAttribute("authType", authType);
		model.addAttribute("registerType", registerType);
		return "/wifi/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(String userPassword, String phoneNum,
			String phoneCode, String wifidogHost, String wifidogPort,
			String gw_id, Model model) {
		AuthType authType = authTypeService.getEnabledAuthType();
		boolean result = userService.registerUser(userPassword, phoneNum,
				phoneCode, authType.getRegisterType());
		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		model.addAttribute("gw_id", gw_id);
		model.addAttribute("authType", authType.getAuthType());
		model.addAttribute("registerType", authType.getRegisterType());
		model.addAttribute("result", result);
		return "/wifi/register";
	}
}
