package net.dasherz.wifiwolf.wifidog.controller;

import net.dasherz.wifiwolf.common.util.Constants;
import net.dasherz.wifiwolf.common.util.DictUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/gw_message.php")
public class MessageController {
	private static final Logger logger = LoggerFactory
			.getLogger(MessageController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String handleMessage(String message, Model model) {
		model.addAttribute("message", DictUtils.getName(
				Constants.DICT_GROUP_GW_MESSAGE, message, message));
		logger.debug("message: " + message);
		return "/wifi/showMessage";
	}
}
