package net.dasherz.wifiwolf.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.dasherz.wifiwolf.common.controller.BaseController;
import net.dasherz.wifiwolf.common.util.PageInfo;
import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.service.AuthTypeService;
import net.dasherz.wifiwolf.service.NodeService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/manage")
public class NodeController extends BaseController {

	@Inject
	private NodeService nodeService;

	@Inject
	private AuthTypeService authTypeService;

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
		int pageNum = 1;
		if (StringUtils.isNumeric(currentPage)) {
			pageNum = Integer.parseInt(currentPage);
		}

		Page<Node> nodes = nodeService.searchNodes(pageNum, PageInfo.PAGE_SIZE,
				node);

		PageInfo pageInfo = new PageInfo(nodes.getTotalPages(), pageNum);

		model.addAttribute("nodes", nodes.getContent());
		model.addAttribute("totalPages", pageInfo.getTotalPage());
		model.addAttribute("page", pageNum);
		model.addAttribute("pageList", pageInfo.getPageList());
		return "/manage/nodeList";
	}

	@RequestMapping(value = "/nodeForm")
	public void form() {

	}

	@RequestMapping(value = "/saveNode")
	public void save() {

	}

}
