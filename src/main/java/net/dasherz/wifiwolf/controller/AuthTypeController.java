package net.dasherz.wifiwolf.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;

import net.dasherz.wifiwolf.common.controller.BaseController;
import net.dasherz.wifiwolf.common.shiro.Encodes;
import net.dasherz.wifiwolf.common.util.Constants;
import net.dasherz.wifiwolf.common.util.PropertiesUtil;
import net.dasherz.wifiwolf.domain.AuthPage;
import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.domain.PortalPage;
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

		AuthPage authPage = authPageService.getAuthPage();
		if (authPage.getCustomizeHtml() != null) {
			authPage.setCustomizeHtml(authPage.getCustomizeHtml());
		}
		model.addAttribute("authPage", authPage);

		PortalPage portalPage = portalPageService.getPortalPage();
		if (portalPage.getCustomizeHtml() != null) {
			portalPage.setCustomizeHtml(portalPage.getCustomizeHtml());
		}
		model.addAttribute("portalPage", portalPage);
		model.addAttribute("authPageTemplates",
				pageTemplateService.findAuthPageTemplate());
		model.addAttribute("portalPageTemplates",
				pageTemplateService.findPortalPageTemplate());
		return "/manage/authType";
	}

	@RequestMapping(value = "/saveAuthType", method = RequestMethod.GET)
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

	@RequestMapping(value = "/saveAuthSetting")
	public String saveSetting(String authTemplate, String authPageCustomizeUrl,
			String authPageType, String authPageCustomizeHtml,
			String portalTemplate, String portalPageCustomizeUrl,
			String portalPageType, String portalPageCustomizeHtml, Model model)
			throws IOException {
		// validate the params
		if (!authTemplate.startsWith(Constants.TEMPLATE_AUTH_PAGE_PREFIX)) {
			addMessage(model, "数据验证失败,authTemplate不合法");
			return list(model);
		}
		// set auth page setting
		AuthPage authPage = authPageService.getAuthPage();
		authPage.setPageTemplate(null);
		authPage.setCustomizeUrl(null);
		authPage.setCustomizeHtml(null);
		if (authPageType.equals(Constants.PAGE_TYPE_USE_TEMPLATE)) {
			Long id = Long.parseLong(authTemplate
					.substring(Constants.TEMPLATE_AUTH_PAGE_PREFIX.length()));
			authPage.setPageTemplate(pageTemplateService.getPageTemplate(id));
		} else if (authPageType.equals(Constants.PAGE_TYPE_USE_CUSTOMIZE_URL)) {
			authPage.setCustomizeUrl(authPageCustomizeUrl);

		} else if (authPageType.equals(Constants.PAGE_TYPE_USE_CUSTOMIZE_HTML)) {
			authPage.setCustomizeHtml(authPageCustomizeHtml);
			saveHtmlToFile(Encodes.unescapeHtml(authPageCustomizeHtml),
					"resources/custom/customAuthPage.htm");
		}

		// set portal page setting
		PortalPage portalPage = portalPageService.getPortalPage();
		portalPage.setUseOriginUrl(null);
		portalPage.setPageTemplate(null);
		portalPage.setCustomizeUrl(null);
		portalPage.setCustomizeHtml(null);
		if (portalPageType.equals(Constants.PAGE_TYPE_USE_ORIGINAL_URL)) {
			portalPage.setUseOriginUrl(1);
		} else if (portalPageType.equals(Constants.PAGE_TYPE_USE_TEMPLATE)) {
			Long id = Long.parseLong(portalTemplate
					.substring(Constants.TEMPLATE_PORTAL_PAGE_PREFIX.length()));
			portalPage.setPageTemplate(pageTemplateService.getPageTemplate(id));
		} else if (portalPageType.equals(Constants.PAGE_TYPE_USE_CUSTOMIZE_URL)) {
			portalPage.setCustomizeUrl(portalPageCustomizeUrl);
		} else if (portalPageType
				.equals(Constants.PAGE_TYPE_USE_CUSTOMIZE_HTML)) {
			portalPage.setCustomizeHtml(portalPageCustomizeHtml);
			saveHtmlToFile(Encodes.unescapeHtml(portalPageCustomizeHtml),
					"resources/custom/customPortalPage.htm");
		}

		authPageService.save(authPage);
		portalPageService.save(portalPage);
		return "redirect:/manage/authType";

	}

	private void saveHtmlToFile(String authPageCustomizeHtml, String path)
			throws IOException {
		File file = new File(PropertiesUtil.getInstance().getProperty(
				"web.root")
				+ path);
		FileWriter writer = new FileWriter(file);
		writer.write(authPageCustomizeHtml);
		writer.flush();
		writer.close();

	}
}
