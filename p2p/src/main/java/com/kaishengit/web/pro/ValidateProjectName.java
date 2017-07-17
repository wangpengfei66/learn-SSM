package com.kaishengit.web.pro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.ProjectService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/validate/projectName")
public class ValidateProjectName extends BaseServlet{

	private static final long serialVersionUID = 1L;
	ProjectService proService = new ProjectService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String projectName = req.getParameter("projectName");
		projectName = StringUtils.isoToUtf(projectName);
		boolean res = proService.validateProjectName(projectName);
		sendText(res,resp);
	}
}
