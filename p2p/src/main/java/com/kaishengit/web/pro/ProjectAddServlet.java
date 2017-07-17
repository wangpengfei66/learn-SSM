package com.kaishengit.web.pro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.ProjectService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/pro/add")
public class ProjectAddServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;

	ProjectService proService = new ProjectService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward("pro/projectAdd", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		
		String projectName = req.getParameter("projectName");
		String projectNo = req.getParameter("projectNo");
		String money = req.getParameter("money");
		String rate = req.getParameter("rate");
		String month = req.getParameter("month");
		String signDate = req.getParameter("signDate");
		String endDate = req.getParameter("endDate");
		try{
			proService.addProject(projectName,projectNo,Double.parseDouble(money),Double.parseDouble(money),rate,Integer.parseInt(month),signDate,endDate);
			res = new Result("success");
		}catch(ServiceException e){
			res = new Result("error",e.getMessage());
		}
		sendJson(res, resp);
	}
}
