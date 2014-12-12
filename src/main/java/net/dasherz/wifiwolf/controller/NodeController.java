package net.dasherz.wifiwolf.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.dasherz.wifiwolf.common.controller.BaseController;
import net.dasherz.wifiwolf.common.util.PageInfo;
import net.dasherz.wifiwolf.domain.Connection;
import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.domain.User;
import net.dasherz.wifiwolf.service.ConnectionService;
import net.dasherz.wifiwolf.service.NodeService;
import net.dasherz.wifiwolf.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/manage")
public class NodeController extends BaseController {

	@Inject
	private NodeService nodeService;

	@Inject
	private UserService userService;

	@Inject
	private ConnectionService connectionService;

	@ModelAttribute
	public Node get(@RequestParam(required = false) Long id) {
		if (id != null) {
			return nodeService.getNode(id);
		} else {
			return new Node();
		}
	}

	@RequestMapping(value = "/nodeList")
	public String list(Node node, HttpServletRequest request, Model model) {
		String currentPage = request.getParameter("page");
		String isConditionChanged = request.getParameter("isConditionChanged");
		int pageNum = 1;
		if (isConditionChanged != null
				&& isConditionChanged.equalsIgnoreCase("false")
				&& StringUtils.isNumeric(currentPage)) {
			pageNum = Integer.parseInt(currentPage);
		}

		Page<Node> nodes = nodeService.searchNodes(pageNum, PageInfo.PAGE_SIZE,
				node);

		PageInfo pageInfo = new PageInfo(nodes.getTotalPages(), pageNum);

		Map<String, Integer> onlineUserMap = getOnlineUserMap(nodes
				.getContent());
		Map<String, Integer> registeredUserMap = getRegisteredUserMap(nodes
				.getContent());
		model.addAttribute("onlineUserMap", onlineUserMap);
		model.addAttribute("registeredUserMap", registeredUserMap);
		model.addAttribute("nodes", nodes.getContent());
		model.addAttribute("totalPages", pageInfo.getTotalPage());
		model.addAttribute("page", pageNum);
		model.addAttribute("pageList", pageInfo.getPageList());
		return "/manage/nodeList";
	}

	private Map<String, Integer> getRegisteredUserMap(List<Node> nodes) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Node node : nodes) {
			map.put(String.valueOf(node.getId()),
					nodeService.getRegisterUserCount(node));
		}
		return map;
	}

	private Map<String, Integer> getOnlineUserMap(List<Node> nodes) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Node node : nodes) {
			map.put(String.valueOf(node.getId()),
					connectionService.getLiveConnectionCount(node));
		}
		return map;
	}

	@RequestMapping(value = "/nodeForm", method = RequestMethod.GET)
	public String form(Node node, Model model) {
		model.addAttribute("node", node);

		return "/manage/nodeForm";
	}

	@RequestMapping(value = "/deleteNode", method = RequestMethod.GET)
	public String delete(Node node, Model model,
			RedirectAttributes redirectAttributes) {
		if (node.getId() != null) {
			nodeService.remove(node.getId());
		}
		addMessage(redirectAttributes, "删除路由器成功");
		return "redirect:/manage/nodeList";
	}

	@RequestMapping(value = "/saveNode", method = RequestMethod.POST)
	public String save(Model model, Node node,
			RedirectAttributes redirectAttributes) {
		if (node.getOwner() == null) {
			node.setOwner(userService.findUserByUsername(SecurityUtils
					.getSubject().getPrincipal().toString()));
		}

		String result = validateNodeInfo(model, node);
		if (!result.equalsIgnoreCase("success")) {
			return result;
		}

		if (node.getId() == null) {
			addMessage(redirectAttributes, "新的路由器'" + node.getNodeDescription()
					+ "'已经注册成功！");
		} else {
			addMessage(redirectAttributes, "路由器'" + node.getNodeDescription()
					+ "'信息已经保存成功！");
		}
		nodeService.save(node);
		return "redirect:/manage/nodeList";

	}

	@RequestMapping(value = "/liveConnectionFragment", method = RequestMethod.GET)
	public String getLiveConnection(String nodeid, Model model)
			throws IOException {
		Node node = nodeService.getNode(Long.parseLong(nodeid));
		List<Connection> connections = connectionService
				.getLiveConnection(node);
		model.addAttribute("node", node);
		model.addAttribute("connections", connections);
		return "/manage/connectionTable";
	}

	@RequestMapping(value = "/liveNodeUserFragment", method = RequestMethod.GET)
	public String getRegisterUser(String nodeid, Model model)
			throws IOException {
		Node node = nodeService.getNode(Long.parseLong(nodeid));
		List<User> nodeUsers = userService.getNodeUsers(node);
		model.addAttribute("node", node);
		model.addAttribute("nodeUsers", nodeUsers);
		return "/manage/nodeUserTable";
	}

	private String validateNodeInfo(Model model, Node node) {
		if (!beanValidator(model, node)) {
			return form(node, model);
		}
		if (node.getGatewayId() != null) {
			Node nodeInDb = nodeService.findByGatewayId(node.getGatewayId());
			if (node.getId() != null) {
				// Update Node validation
				if ((nodeInDb != null)
						&& (nodeInDb.getId().longValue() != node.getId()
								.longValue())) {
					addMessage(model, "数据验证失败,网关ID已存在！");
					return form(node, model);
				}
			} else {
				// Create Node validation
				if (nodeInDb != null) {
					addMessage(model, "数据验证失败,网关ID已存在！");
					return form(node, model);
				}
			}

		}
		if (node.getNodeDescription() != null) {
			Node nodeInDb = nodeService.findByNodeName(node
					.getNodeDescription());
			if (node.getId() != null) {
				// Update Node validation
				if ((nodeInDb != null)
						&& (nodeInDb.getId().longValue() != node.getId()
								.longValue())) {
					addMessage(model, "数据验证失败,路由器名已存在！");
					return form(node, model);
				}
			} else {
				if (nodeInDb != null) {
					addMessage(model, "数据验证失败,路由器名已存在！");
					return form(node, model);
				}
			}
		}
		return "success";
	}
}
