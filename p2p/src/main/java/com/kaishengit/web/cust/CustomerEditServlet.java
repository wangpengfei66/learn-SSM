package com.kaishengit.web.cust;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/cust/edit")
public class CustomerEditServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CustomerService custService = new CustomerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Customer cust = custService.findById(id);
		req.setAttribute("cust", cust);
		forward("cust/custEdit", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		
		String id = req.getParameter("id");
		String tel = req.getParameter("tel");
		String bankNo = req.getParameter("bankNo");
		String bankName = req.getParameter("bankName");
		String address = req.getParameter("address");
		String remark = req.getParameter("remark");
		try{
			custService.updateCustomer(tel,bankNo,bankName,address,remark,Integer.parseInt(id));
			res = new Result("success");
		}catch(ServiceException e) {
			res = new Result("error",e.getMessage());
		}
		sendJson(res,resp);
	}
	
}
