package net.dasherz.wifiwolf.wifidog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HeartBeatingController {
	private static final Logger logger = LoggerFactory
			.getLogger(HeartBeatingController.class);

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public void ping(@RequestParam String gw_id, @RequestParam long sys_uptime,
			@RequestParam long sys_memfree, @RequestParam float sys_load,
			@RequestParam long wifidog_uptime, HttpServletResponse response)
			throws IOException {
		logger.debug("gateway id: " + gw_id);
		logger.debug("sys_uptime: " + sys_uptime);
		logger.debug("sys_memfree: " + sys_memfree);
		logger.debug("sys_load: " + sys_load);
		logger.debug("wifidog_uptime: " + wifidog_uptime);
		response.getWriter().write("Pong");
	}
}
