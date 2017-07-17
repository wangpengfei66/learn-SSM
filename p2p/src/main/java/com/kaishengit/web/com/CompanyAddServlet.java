package com.kaishengit.web.com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CompanyService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/com/add")
public class CompanyAddServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CompanyService comService = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> comCityList = comService.getComCityList();
		req.setAttribute("comCityList", comCityList);
		forward("com/companyAdd", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		String comName = req.getParameter("name");
		String tel = req.getParameter("tel");
		String city = req.getParameter("city");
		String address = req.getParameter("address");
		String remark = req.getParameter("remark");
		String head = req.getParameter("head");
		//存到数据库，返回json数据到前端页面
		try{
			comService.save(comName,tel,city,address,remark,head);
			res = new Result("success");
		}catch(ServiceException e){
			res = new Result("error",e.getMessage());
		}
		sendJson(res,resp);
	}
}
