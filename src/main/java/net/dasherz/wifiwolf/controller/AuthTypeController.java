package net.dasherz.wifiwolf.controller;

import javax.inject.Inject;

import net.dasherz.wifiwolf.common.controller.BaseController;
import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.service.AuthPageService;
import net.dasherz.wifiwolf.service.AuthTypeService;
import net.dasherz.wifiwolf.service.PageTemplateService;
import net.dasherz.wifiwolf.service.PortalPageService;

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

	@Inject
	private AuthPageService authPageService;

	@Inject
	private PortalPageService portalPageService;

	@Inject
	private PageTemplateService pageTemplateService;

	@ModelAttribute
	public AuthType get(@RequestParam(required = false) Long id) {
		if (id != null) {
			return authTypeService.getAuthType(id);
		} else {
			return new AuthType();
		}
	}

	@RequestMapping(value = "/authType")
	public String list(Model model) {
		model.addAttribute("authTypes", authTypeService.getAllAuthTypes());
		model.addAttribute("authPage", authPageService.getAuthPage());
		model.addAttribute("portalPage", portalPageService.getPortalPage());
		model.addAttribute("pageTemplates", pageTemplateService.findAll());
		return "/manage/authType";
	}

	@RequestMapping(value = "/authTypeStatus", method = RequestMethod.GET)
	public String changeAuthTypeStatus(AuthType authType, Model model,
			RedirectAttributes redirectAttributes) {
		if (authType.getId() != null) {
			if (authType.getStatus() == 1) {
				authTypeService.disableAuthType(authType);
				addMessage(redirectAttributes, "禁用该认证方式成功。");
			} else {
				authTypeService.enableAuthType(authType.getId());
				addMessage(redirectAttributes, "启用该认证方式成功。");
			}
		} else {
			addMessage(redirectAttributes, "请选择一种认证方式");
		}
		return "redirect:/manage/authType";
	}
}
