/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package net.dasherz.wifiwolf.common.shiro;

import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class ShiroDbRealm extends AuthorizingRealm {

	// protected AccountService accountService;
	protected UserService userService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = userService.findUserByUsername(token.getUsername());
		if (user != null) {

			if (user.getUserType() == 0) {
				throw new AuthenticationException("您没有权限登录此系统");
			}

			String password = user.getPassword();
			if (password == null)
				throw new AuthenticationException("account error...");
			AuthenticationInfo info = new SimpleAuthenticationInfo(user,
					password, getName());
			// this.setSession("user", user);

			return info;
		} else {
			return null;
		}
	}

	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		User userInDb = userService.findUserByUsername(user.getUsername());
		if (userInDb != null && userInDb.getUserType() == 1) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			// info.addRoles("admin");
			return info;
		} else {
			return null;
		}
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
