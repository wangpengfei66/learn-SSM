package com.kaishengit.web.cust;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Project;
import com.kaishengit.entity.Result;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/cust/info")
public class CustomerInfoServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CustomerService custService = new CustomerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		String custId = req.getParameter("id");
		if(StringUtils.isNumeric(custId)) {
			Customer cust = custService.findById(custId);
			res = new Result("success",cust);
		}else{
			res = new Result("error","参数错误");
		}
		sendJson(res, resp);
	}
	
}
