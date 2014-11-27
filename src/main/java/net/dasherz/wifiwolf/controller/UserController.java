/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package net.dasherz.wifiwolf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.dasherz.wifiwolf.common.util.CacheUtils;
import net.dasherz.wifiwolf.service.UserService;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
public class UserController {

	@Inject
	private UserService userService;

	private int currentPage = 1;

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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listUsers(Integer currentPage, Model model) {

		int userCount = userService.getUserCount();
		if (currentPage != null) {
			this.currentPage = currentPage.intValue();
		}
		int totalPage = userCount / UserService.PAGE_SIZE;
		if (userCount % UserService.PAGE_SIZE != 0) {
			totalPage += 1;
		}
		List<Integer> pageList = new ArrayList<Integer>();
		int start = 1;
		if (this.currentPage >= 10) {
			start = this.currentPage / 10 * 10;
		}
		int num = start;
		while (!(num > totalPage || num > start + 10)) {
			pageList.add(new Integer(num));
			++num;
		}
		model.addAttribute("users", userService.getPageUsers(this.currentPage)
				.getContent());
		model.addAttribute("totalPages", totalPage);
		model.addAttribute("page", this.currentPage);
		model.addAttribute("pageList", pageList);
		return "/user/list";
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
