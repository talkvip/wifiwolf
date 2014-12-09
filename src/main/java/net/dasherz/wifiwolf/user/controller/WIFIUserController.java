package net.dasherz.wifiwolf.user.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.dasherz.wifiwolf.common.util.Constants;
import net.dasherz.wifiwolf.common.util.DictUtils;
import net.dasherz.wifiwolf.common.util.SentSMSResult;
import net.dasherz.wifiwolf.common.util.ValidationCode;
import net.dasherz.wifiwolf.domain.AuthPage;
import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.domain.PhoneUser;
import net.dasherz.wifiwolf.domain.Token;
import net.dasherz.wifiwolf.service.AuthPageService;
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

	@Inject
	private AuthPageService authPageService;

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
	public String login(@RequestParam(required = false) String username,
			String userPassword, String phoneNum, String phoneCode,
			String wifidogHost, String wifidogPort, String gw_id,
			String originUrl, Model model, HttpSession session,
			HttpServletResponse repsonse, HttpServletRequest request)
			throws IOException {

		AuthType authType = authTypeService.getEnabledAuthType();
		ValidationCode code = userService.validateUser(username, userPassword,
				phoneNum, phoneCode, authType.getAuthType());

		if (code == ValidationCode.VALID) {
			PhoneUser phoneUser = null;
			// for PHONE type, store phone number, register automatically.
			if (authType.getAuthType().equals(Constants.AUTH_TYPE_PHONE)
					|| authType.getAuthType().equals(
							Constants.AUTH_TYPE_PHONE_SMS)) {
				// phoneUserService.savePhoneNumber(phoneNum);
				userService.registerUserAutomatically(phoneNum, phoneCode);
			}

			if (authType.getAuthType().equals(
					Constants.AUTH_TYPE_PHONE_PASSWORD_SMS)
					|| authType.getAuthType().equals(
							Constants.AUTH_TYPE_PHONE_SMS)) {
				phoneUser = phoneUserService.findByPhoneNum(phoneNum);
				// set phone verify code to verified after validation for SMS
				// mode
				phoneUserService.verifiedForPhoneNumber(phoneUser);
			}

			Token token = tokenService.createToken(authType,
					userService.findUserByUsername(username), phoneUser,
					nodeService.findByGatewayId(gw_id), originUrl);

			session.setAttribute(Constants.SESSION_ATTR_WIFIDOG_HOST,
					wifidogHost);
			session.setAttribute(Constants.SESSION_ATTR_WIFIDOG_PORT,
					wifidogPort);
			session.setAttribute(Constants.SESSION_ATTR_TOKEN_ID, token.getId());
			return "redirect:http://" + wifidogHost + ":" + wifidogPort
					+ "/wifidog/auth?token=" + token.getToken();
		}

		AuthPage authPage = authPageService.getAuthPage();
		if (authPage.getPageTemplate() == null) {
			outputErrorMessage(repsonse, code);
			return null;
		}

		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		model.addAttribute("gw_id", gw_id);
		model.addAttribute("authType", authType.getAuthType());
		model.addAttribute("registerType", authType.getRegisterType());
		model.addAttribute("originUrl", originUrl);
		model.addAttribute("phoneNum", phoneNum);
		model.addAttribute("username", username);
		model.addAttribute("message",
				DictUtils.getName("validation_code", code.name(), "系统错误。"));

		return "/wifi/" + authPage.getPageTemplate().getTemplatePath();
	}

	private void outputErrorMessage(HttpServletResponse repsonse,
			ValidationCode code) throws IOException {
		repsonse.getWriter()
				.write("<html><head><title>错误</title></head><body>"
						+ DictUtils.getName("validation_code", code.name(),
								"系统错误。")
						+ "<a href='javascript:void(0)' onclick='history.go(-1)'>返回</a></body>");
	}

	@RequestMapping(value = "/login/phoneVerify")
	public void phoneVerify(
			@RequestParam(value = "phoneNum", required = true) String phoneNum,
			HttpServletResponse response) throws IOException {
		SentSMSResult result = phoneUserService.sendPhoneMessage(phoneNum);
		response.getWriter().write(result.name());
	}

	// for customize auth page only
	@RequestMapping(value = "/login/sendVerifyCode")
	public void getPhoneVerifyCode(
			@RequestParam(value = "phoneNum", required = true) String phoneNum,
			HttpServletResponse response) throws IOException {
		SentSMSResult result = phoneUserService.sendPhoneMessage(phoneNum);
		String message = DictUtils.getName("sms-validation", result.name(),
				"系统错误。");
		if (result == SentSMSResult.SUCCESS) {
			message = null;
		}
		response.setContentType("application/javascript");
		if (message == null) {
			response.getWriter()
					.write("SmsVerifyLock = true;setTimeout('SmsVerifyLock=false', 60000);");
		} else {
			response.getWriter().write("alert('" + message + "');");
		}
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
		ValidationCode code = userService.registerUser(userPassword, phoneNum,
				phoneCode, authType.getRegisterType());
		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		model.addAttribute("gw_id", gw_id);
		model.addAttribute("authType", authType.getAuthType());
		model.addAttribute("registerType", authType.getRegisterType());
		if (code == ValidationCode.VALID) {
			model.addAttribute("message", "恭喜您已经完成注册，请登陆系统使用吧！");
		} else {
			model.addAttribute("message",
					DictUtils.getName("validation_code", code.name(), "系统错误。"));
		}
		return "/wifi/register";
	}
}
