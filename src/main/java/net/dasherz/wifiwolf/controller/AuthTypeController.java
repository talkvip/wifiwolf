package net.dasherz.wifiwolf.controller;

import java.util.List;

import javax.inject.Inject;

import net.dasherz.wifiwolf.common.controller.BaseController;
import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.service.AuthTypeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/manage")
public class AuthTypeController extends BaseController {

	@Inject
	private AuthTypeService authTypeService;

	@ModelAttribute
	public AuthType get(@RequestParam(required = false) Long id) {
		if (id != null) {
			return authTypeService.getAuthType(id);
		} else {
			return new AuthType();
		}
	}

	@RequestMapping(value = "/authTypeList")
	public String list(Model model) {
		model.addAttribute("authTypes", authTypeService.getAllAuthTypes());
		return "/manage/authTypeList";
	}

	@RequestMapping(value = "/authTypeStatus", method = RequestMethod.GET)
	public String changeAuthTypeStatus(AuthType authType, Model model,
			RedirectAttributes redirectAttributes) {
		if (authType.getId() != null) {

			if (authType.getStatus() == 1) {
				authType.setStatus(2);
				authTypeService.save(authType);
				addMessage(redirectAttributes, "禁用该认证方式成功。");
			} else {
				List<AuthType> authtypes = authTypeService.getAllAuthTypes();
				for (AuthType type : authtypes) {
					if (authType.getId() == type.getId()) {
						type.setStatus(1);
					} else {
						type.setStatus(2);
					}
				}
				authTypeService.saveAll(authtypes);
				addMessage(redirectAttributes, "启用该认证方式成功。");
			}
		} else {
			addMessage(redirectAttributes, "请选择一种认证方式");
		}
		return "redirect:/manage/authTypeList";
	}
}
