package net.dasherz.wifiwolf.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.dasherz.wifiwolf.common.util.PageInfo;
import net.dasherz.wifiwolf.domain.Log;
import net.dasherz.wifiwolf.service.LogService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/manage")
public class LogController {

	@Inject
	private LogService logService;

	@RequestMapping(value = "/listLog")
	public String list(Model model, HttpServletRequest request, Log log) {
		String currentPage = request.getParameter("page");
		int pageNum = 1;
		if (StringUtils.isNumeric(currentPage)) {
			pageNum = Integer.parseInt(currentPage);
		}
		Page<Log> logs = logService.getLogs(pageNum, PageInfo.PAGE_SIZE, log);
		PageInfo pageInfo = new PageInfo(logs.getTotalPages(), pageNum);

		model.addAttribute("log", log);
		model.addAttribute("logs", logs.getContent());
		model.addAttribute("totalPages", pageInfo.getTotalPage());
		model.addAttribute("page", pageNum);
		model.addAttribute("pageList", pageInfo.getPageList());
		return "/manage/logList";
	}

}
