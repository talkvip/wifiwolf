/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package net.dasherz.wifiwolf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.dasherz.wifiwolf.common.controller.BaseController;
import net.dasherz.wifiwolf.common.util.CacheUtils;
import net.dasherz.wifiwolf.common.util.PageInfo;
import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.service.NodeService;
import net.dasherz.wifiwolf.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
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
public class UserController extends BaseController {

	@Inject
	private UserService userService;

	@Inject
	private NodeService nodeService;

	@ModelAttribute
	public User get(@RequestParam(required = false) Long id) {
		if (id != null) {
			return userService.getUser(id);
		} else {
			return new User();
		}
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login() {
		if (userService.getCurrentUserName().isEmpty()) {
			return "/user/login";
		} else {

			return "redirect:/";
		}
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				username);
		model.addAttribute("isValidateCodeLogin",
				isValidateCodeLogin(username, true, false));
		return "/user/login";
	}

	@RequestMapping(value = "/manage/userList")
	public String listUsers(User user, HttpServletRequest request, Model model) {

		String currentPage = request.getParameter("page");
		String isConditionChanged = request.getParameter("isConditionChanged");
		int pageNum = 1;
		if (isConditionChanged != null
				&& isConditionChanged.equalsIgnoreCase("false")
				&& StringUtils.isNumeric(currentPage)) {
			pageNum = Integer.parseInt(currentPage);
		}

		Page<User> users = userService.searchUsers(pageNum, PageInfo.PAGE_SIZE,
				user);

		PageInfo pageInfo = new PageInfo(users.getTotalPages(), pageNum);

		Map<String, String> userRegisterNodeMap = getUserRegisterNodeMap(users
				.getContent());

		model.addAttribute("nodes", nodeService.findAll());
		model.addAttribute("userRegisterNodeMap", userRegisterNodeMap);
		model.addAttribute("users", users.getContent());
		model.addAttribute("totalPages", pageInfo.getTotalPage());
		model.addAttribute("page", pageNum);
		model.addAttribute("pageList", pageInfo.getPageList());
		return "/manage/userList";
	}

	@RequestMapping(value = "/manage/userForm", method = RequestMethod.GET)
	public String form(User user, Model model) {
		model.addAttribute("user", user);
		return "/manage/userForm";
	}

	@RequestMapping(value = "/user/myinfo", method = RequestMethod.GET)
	public String myInfo(Model model) {
		String username = SecurityUtils.getSubject().getPrincipal().toString();
		User user = userService.findUserByUsername(username);
		model.addAttribute("user", user);
		return "/user/userForm";
	}

	@RequestMapping(value = "/user/saveInfo")
	public String saveNormalUser(User user, HttpServletRequest request,
			Model model, RedirectAttributes redirectAttributes) {
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			user.setPassword(UserService.entryptPassword(user
					.getPlainPassword()));
		}
		if (user.getId() == null) {
			addMessage(model, "数据验证失败,请重试！");
			return form(user, model);
		}
		if (!beanValidator(model, user)) {
			return form(user, model);
		}
		// restore major properties
		User oldUser = userService.getUser(user.getId());
		user.setAccountStatus(oldUser.getAccountStatus());
		user.setWifiStatus(oldUser.getWifiStatus());
		user.setUserType(oldUser.getUserType());
		user.setIsPhoneVerified(oldUser.getIsPhoneVerified());
		user.setIsEmailVerified(oldUser.getIsEmailVerified());
		userService.updateUser(user);
		addMessage(redirectAttributes, "保存用户'" + user.getUsername() + "'成功");
		return "redirect:/user/myinfo";
	}

	@RequestMapping(value = "/manage/saveUser")
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
		if (user.getId() == null
				&& userService.findUserByUsername(user.getUsername()) != null) {
			addMessage(model, "数据验证失败,用户名已存在！");
			return form(user, model);
		}
		if (user.getId() == null
				&& StringUtils.isEmpty(user.getPlainPassword())) {
			addMessage(model, "数据验证失败,请填入密码！");
			return form(user, model);
		}
		if (user.getId() == null) {
			userService.createUser(user);
		} else {
			userService.updateUser(user);
		}
		addMessage(redirectAttributes, "保存用户'" + user.getUsername() + "'成功");
		return "redirect:/manage/userForm?id=" + user.getId();
	}

	@RequestMapping(value = "/manage/deleteUser", method = RequestMethod.GET)
	public String delete(User user, Model model,
			RedirectAttributes redirectAttributes) {
		if (user.getId() != null) {
			userService.remove(user.getId());
		}
		addMessage(redirectAttributes, "删除用户成功");
		return "redirect:/manage/userList";
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

	private Map<String, String> getUserRegisterNodeMap(List<User> users) {
		Map<String, String> map = new HashMap<String, String>();
		for (User user : users) {
			String name = userService.getUserRegisterNodeName(user);
			map.put(String.valueOf(user.getId()), name);
		}
		return map;
	}
}
