package com.kaishengit.web.pro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Project;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.ProjectService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/pro/info")
public class ProjectInfoServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	ProjectService proService = new ProjectService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		String proId = req.getParameter("id");
		if(StringUtils.isNumeric(proId)) {
			Project pro = proService.findById(proId);
			System.out.println(pro);
			res = new Result("success",pro);
		}else{
			res = new Result("error","参数错误");
		}
		sendJson(res, resp);
	}
}
