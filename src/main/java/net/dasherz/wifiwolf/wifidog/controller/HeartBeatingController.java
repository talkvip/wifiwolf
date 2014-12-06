package net.dasherz.wifiwolf.wifidog.controller;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.service.NodeService;

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

	@Inject
	NodeService nodeService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public void ping(@RequestParam String gw_id, @RequestParam int sys_uptime,
			@RequestParam int sys_memfree, @RequestParam float sys_load,
			@RequestParam int wifidog_uptime, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.debug("gateway id: " + gw_id);
		logger.debug("sys_uptime: " + sys_uptime);
		logger.debug("sys_memfree: " + sys_memfree);
		logger.debug("sys_load: " + sys_load);
		logger.debug("wifidog_uptime: " + wifidog_uptime);

		Node node = nodeService.findByGatewayId(gw_id);
		node.setLastHeartbeatWifidogUptime(wifidog_uptime);
		node.setLastHeartbeatSysLoad(sys_load);
		node.setLastHeartbeatSysMemfree(sys_memfree);
		node.setLastHeartbeatSysUptime(sys_uptime);
		node.setLastHeartbeatTimestamp(new Date());
		node.setLastHeartbeatIp(request.getRemoteAddr());

		nodeService.save(node);
		response.getWriter().write("Pong");
	}
}
