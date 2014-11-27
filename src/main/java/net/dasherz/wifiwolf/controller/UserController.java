/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package net.dasherz.wifiwolf.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.dasherz.wifiwolf.common.controller.BaseController;
import net.dasherz.wifiwolf.common.util.CacheUtils;
import net.dasherz.wifiwolf.common.util.PageInfo;
import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完成,
 * 
 * @author Jonas
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@Inject
	private UserService userService;

	@ModelAttribute
	public User get(@RequestParam(required = false) Long id) {
		if (id != null) {
			return userService.getUser(id);
		} else {
			return new User();
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		if (userService.getCurrentUserName().isEmpty()) {
			return "/user/login";
		} else {

			return "redirect:/";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				username);
		model.addAttribute("isValidateCodeLogin",
				isValidateCodeLogin(username, true, false));
		return "/user/login";
	}

	@RequestMapping(value = "/list")
	public String listUsers(User user, HttpServletRequest request, Model model) {

		String currentPage = request.getParameter("page");
		int pageNum = 1;
		if (StringUtils.isNumeric(currentPage)) {
			pageNum = Integer.parseInt(currentPage);
		}

		Page<User> users = userService.searchUsers(pageNum, PageInfo.PAGE_SIZE,
				user);

		PageInfo pageInfo = new PageInfo(users.getTotalPages(), pageNum);

		model.addAttribute("users", users.getContent());
		model.addAttribute("totalPages", pageInfo.getTotalPage());
		model.addAttribute("page", pageNum);
		model.addAttribute("pageList", pageInfo.getPageList());
		return "/user/list";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(User user, Model model) {
		model.addAttribute("user", user);
		return "/user/userForm";
	}

	@RequestMapping(value = "save")
	public String save(User user, HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			user.setPassword(UserService.entryptPassword(user
					.getPlainPassword()));
		}
		if (!beanValidator(model, user)) {
			return form(user, model);
		}
		if (user.getId() == null) {
			userService.createUser(user);
		} else {
			userService.updateUser(user);
		}
		addMessage(redirectAttributes, "保存用户'" + user.getUsername() + "'成功");
		return "redirect:/user/form?id=" + user.getId();
	}

	public static boolean isValidateCodeLogin(String useruame, boolean isFail,
			boolean clean) {
		@SuppressWarnings("unchecked")
		Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils
				.get("loginFailMap");
		if (loginFailMap == null) {
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum == null) {
			loginFailNum = 0;
		}
		if (isFail) {
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean) {
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}
}
