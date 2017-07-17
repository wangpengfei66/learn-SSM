package com.kaishengit.web.pro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Project;
import com.kaishengit.service.ProjectService;
import com.kaishengit.util.Page;
import com.kaishengit.web.BaseServlet;
@WebServlet("/pro/list")
public class ProjectListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	ProjectService proService = new ProjectService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		if(StringUtils.isNotEmpty(value)){
			value = com.kaishengit.util.StringUtils.isoToUtf(value);
		}
		
		String page = req.getParameter("page");
		int currentPageNo = 1;
		
		if(StringUtils.isNumeric(page)) {
			currentPageNo = Integer.parseInt(page);
		}
		Page<Project> resPage = proService.findProjectPage(key,value,currentPageNo);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		req.setAttribute("page", resPage);
		forward("pro/projectList", req, resp);
	}
	
	
}
