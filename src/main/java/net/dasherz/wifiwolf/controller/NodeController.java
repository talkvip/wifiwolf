package net.dasherz.wifiwolf.controller;

import javax.inject.Inject;

import net.dasherz.wifiwolf.common.controller.BaseController;
import net.dasherz.wifiwolf.domain.Node;
import net.dasherz.wifiwolf.service.AuthTypeService;
import net.dasherz.wifiwolf.service.NodeService;

import org.springframework.stereotype.Controller;
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
	public void list() {

	}

	@RequestMapping(value = "/nodeForm")
	public void form() {

	}

	@RequestMapping(value = "/saveNode")
	public void save() {

	}

}
