package net.dasherz.wifiwolf.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.dasherz.wifiwolf.common.controller.BaseController;
import net.dasherz.wifiwolf.common.util.PageInfo;
import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.service.AuthTypeService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
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
	public String list(HttpServletRequest request, Model model) {
		String currentPage = request.getParameter("page");
		int pageNum = 1;
		if (StringUtils.isNumeric(currentPage)) {
			pageNum = Integer.parseInt(currentPage);
		}

		Page<AuthType> authTypes = authTypeService.getPageAuthTypes(pageNum,
				PageInfo.PAGE_SIZE);

		PageInfo pageInfo = new PageInfo(authTypes.getTotalPages(), pageNum);

		model.addAttribute("authTypes", authTypes.getContent());
		model.addAttribute("totalPages", pageInfo.getTotalPage());
		model.addAttribute("page", pageNum);
		model.addAttribute("pageList", pageInfo.getPageList());
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
				authType.setStatus(1);
				authTypeService.save(authType);
				addMessage(redirectAttributes, "启用该认证方式成功。");
			}
		} else {
			addMessage(redirectAttributes, "请先选择一种认证方式");
		}
		return "redirect:/manage/authTypeList";
	}
}
