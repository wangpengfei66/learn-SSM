package com.kaishengit.web.cust;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/cust/add")
public class AddCustomerServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	CustomerService custService = new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward("cust/custAdd", req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		String idNo = req.getParameter("idNo");
		String bankNo = req.getParameter("bankNo");
		String bankName = req.getParameter("bankName");
		String birthday = req.getParameter("birthday");
		String address = req.getParameter("address");
		String remark = req.getParameter("remark");
		try{
			custService.addCustomer(name,tel,idNo,bankNo,bankName,birthday,address,remark);
			res = new Result("success");
		}catch(ServiceException e) {
			res = new Result("error",e.getMessage());
		}
		sendJson(res,resp);
	}
}
