package com.kaishengit.web.com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Company;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CompanyService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/com/edit")
public class CompanyEditServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CompanyService comService = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> comCityList = comService.getComCityList();
		String id = req.getParameter("id");
		Company com = comService.findById(id);
		req.setAttribute("comCityList", comCityList);
		req.setAttribute("com", com);
		forward("/com/companyEdit", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		String id = req.getParameter("id");
		String comName = req.getParameter("name");
		String tel = req.getParameter("tel");
		String city = req.getParameter("city");
		String address = req.getParameter("address");
		String remark = req.getParameter("remark");
		String head = req.getParameter("head");
		
		try{
			comService.editComById(id,comName,tel,city,address,remark,head);
			res = new Result("success");
		}catch(ServiceException e){
			res = new Result("error",e.getMessage());
		}
		sendJson(res, resp);
	}
}
