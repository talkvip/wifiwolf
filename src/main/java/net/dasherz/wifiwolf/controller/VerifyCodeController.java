package net.dasherz.wifiwolf.controller;

import javax.inject.Inject;

import net.dasherz.wifiwolf.service.PhoneUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VerifyCodeController {

	@Inject
	PhoneUserService phoneUserService;

	@RequestMapping(value = "/manage/verifyCode", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("codes", phoneUserService.findLatest100());
		return "/manage/verifyCode";
	}
}
