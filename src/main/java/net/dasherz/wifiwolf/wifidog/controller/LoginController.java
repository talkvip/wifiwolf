package net.dasherz.wifiwolf.wifidog.controller;

import javax.inject.Inject;

import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.domain.Token;
import net.dasherz.wifiwolf.service.AuthTypeService;
import net.dasherz.wifiwolf.service.NodeService;
import net.dasherz.wifiwolf.service.TokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Inject
	private AuthTypeService authService;

	@Inject
	private TokenService tokenService;

	@Inject
	private NodeService nodeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String login(String gw_id, String gw_address, String gw_port,
			String url, Model model) {
		logger.debug("gateway id: " + gw_id);
		logger.debug("gateway address: " + gw_address);
		logger.debug("gateway port: " + gw_port);
		logger.debug("gateway url: " + url);
		AuthType authType = authService.getEnabledAuthType();
		if (authType == null) {
			model.addAttribute("message",
					"对不起，认证页面无法显示。请尽快通知管理员设置一种验证方式。给您带来的不便，敬请谅解");
			return "showMessage";
		}

		Node node = nodeService.findByGatewayId(gw_id);
		if (node == null) {
			model.addAttribute("message", "对不起，由于该路由器未在认证服务器上注册，暂无法进行认证");
			return "showMessage";
		}

		if (authType.getAuthType().equalsIgnoreCase("NONE")) {
			Token token = tokenService.createToken(authType, null, null, node);
			return "redirect:http://" + gw_address + ":" + gw_port
					+ "/wifidog/auth?token=" + token.getToken();
		} else {
			model.addAttribute("wifidogHost", gw_address);
			model.addAttribute("wifidogPort", gw_port);
			model.addAttribute("authType", authType.getAuthType());
			model.addAttribute("registerType", authType.getRegisterType());
			model.addAttribute("gw_id", gw_id);
			return "/wifi/login";
		}
	}
}
