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
@WebServlet("/cust/del")
public class CustomerDeletServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CustomerService custSerivice = new CustomerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		//需要先确定该客户下有没有积分存款
		String id = req.getParameter("id");
		try{
			custSerivice.delCust(id);
			res = new Result("success");
		}catch(ServiceException e) {
			res = new Result("error",e.getMessage());
		}
		sendJson(res, resp);
	}
}
