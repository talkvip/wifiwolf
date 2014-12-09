package net.dasherz.wifiwolf.wifidog.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.dasherz.wifiwolf.common.util.Constants;
import net.dasherz.wifiwolf.common.util.PropertiesUtil;
import net.dasherz.wifiwolf.domain.AuthPage;
import net.dasherz.wifiwolf.domain.AuthType;
import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.domain.Token;
import net.dasherz.wifiwolf.service.AuthPageService;
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
	private AuthPageService authPageService;

	@Inject
	private TokenService tokenService;

	@Inject
	private NodeService nodeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String login(String gw_id, String gw_address, String gw_port,
			String url, Model model, HttpSession session,
			HttpServletResponse response) {
		logger.debug("gateway id: " + gw_id);
		logger.debug("gateway address: " + gw_address);
		logger.debug("gateway port: " + gw_port);
		logger.debug("gateway url: " + url);
		AuthType authType = authService.getEnabledAuthType();
		if (authType == null) {
			model.addAttribute("message",
					"对不起，认证页面无法显示。请尽快通知管理员设置一种验证方式。给您带来的不便，敬请谅解");
			return "/wifi/showMessage";
		}

		Node node = nodeService.findByGatewayId(gw_id);
		if (node == null) {
			model.addAttribute("message", "对不起，由于该路由器未在认证服务器上注册，暂无法进行认证");
			return "/wifi/showMessage";
		}
		if (authType.getAuthType().equalsIgnoreCase("NONE")) {
			Token token = tokenService.createToken(authType, null, null, node,
					url);
			session.setAttribute(Constants.SESSION_ATTR_WIFIDOG_HOST,
					gw_address);
			session.setAttribute(Constants.SESSION_ATTR_WIFIDOG_PORT, gw_port);
			session.setAttribute(Constants.SESSION_ATTR_TOKEN_ID, token.getId());
			return "redirect:http://" + gw_address + ":" + gw_port
					+ "/wifidog/auth?token=" + token.getToken();
		}
		AuthPage authPage = authPageService.getAuthPage();
		if (authPage.getCustomizeUrl() != null) {
			return getCustomizeUrl(authPage.getCustomizeUrl(), gw_address,
					gw_port, gw_id, url, authType.getAuthType());
		}
		if (authPage.getCustomizeHtml() != null) {
			return getCustomizeUrl(
					PropertiesUtil.getInstance().getProperty("customAuthPage"),
					gw_address, gw_port, gw_id, url, authType.getAuthType());
		}
		if (authPage.getPageTemplate() != null) {
			model.addAttribute("wifidogHost", gw_address);
			model.addAttribute("wifidogPort", gw_port);
			model.addAttribute("authType", authType.getAuthType());
			model.addAttribute("registerType", authType.getRegisterType());
			model.addAttribute("gw_id", gw_id);
			model.addAttribute("originUrl", url);
			return "/wifi/" + authPage.getPageTemplate().getTemplatePath();
		}
		model.addAttribute("wifidogHost", gw_address);
		model.addAttribute("wifidogPort", gw_port);
		model.addAttribute("authType", authType.getAuthType());
		model.addAttribute("registerType", authType.getRegisterType());
		model.addAttribute("gw_id", gw_id);
		model.addAttribute("originUrl", url);
		return "/wifi/login";
	}

	private String getCustomizeUrl(String url, String gw_address,
			String gw_port, String gw_id, String originUrl, String authType) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("redirect:");
		buffer.append(url);
		buffer.append("?");
		buffer.append("server_url=http://");
		buffer.append(PropertiesUtil.getInstance().getProperty("server_url"));
		buffer.append("/wifiwolf/wifi/login");
		buffer.append("&gw_address=");
		buffer.append(gw_address);
		buffer.append("&gw_port=");
		buffer.append(gw_port);
		buffer.append("&gw_id=");
		buffer.append(gw_id);
		buffer.append("&url=");
		buffer.append(originUrl);
		buffer.append("&authType=");
		buffer.append(authType);
		return buffer.toString();
	}
}
