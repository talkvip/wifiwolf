package net.dasherz.wifiwolf.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.dasherz.wifiwolf.wifidog.constant.UserList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, String wifidogHost,
			String wifidogPort, HttpServletResponse response,
			HttpSession session, Model model) throws IOException {
		if (UserList.getUsers().contains(username) || !password.equals("admin")) {
			model.addAttribute("wifidogHost", wifidogHost);
			model.addAttribute("wifidogPort", wifidogPort);
			return "login";
		}
		if (username.length() != 0 && password.equals("admin")) {
			session.setAttribute("userId", username);
			String token = String.valueOf(System.currentTimeMillis());
			UserList.getTokens().put(token, username);
			response.sendRedirect("http://" + wifidogHost + ":" + wifidogPort
					+ "/wifidog/auth?token=" + token);
		}
		model.addAttribute("wifidogHost", wifidogHost);
		model.addAttribute("wifidogPort", wifidogPort);
		return "login";

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<String> list() {
		return UserList.getUsers();

	}

}
