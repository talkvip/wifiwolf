package net.dasherz.wifiwolf;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import net.dasherz.wifiwolf.controller.UserController;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		Subject subject = SecurityUtils.getSubject();

		String username = subject.getPrincipal().toString();
		UserController.isValidateCodeLogin(username, false, true);
		if (subject.hasRole("admin")) {
			return "manage/index";
		} else {
			return "user/index";
		}
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		Subject subject = SecurityUtils.getSubject();
		String username = subject.getPrincipal().toString();
		UserController.isValidateCodeLogin(username, false, true);
		return "home";
	}
}
