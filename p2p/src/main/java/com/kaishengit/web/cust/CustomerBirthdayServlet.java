package com.kaishengit.web.cust;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/cust/bir")
public class CustomerBirthdayServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CustomerService custService = new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String birth = req.getParameter("birth");
		birth = (birth == null ? "1" : birth);
		String page = req.getParameter("page");
		
		int currentPageNo = 1;
		if(StringUtils.isNumeric(page)) {
			currentPageNo = Integer.parseInt(page);
		}
		Page<Customer> resPage = custService.findPage(birth,currentPageNo);
		req.setAttribute("page", resPage);
		forward("cust/custBir", req, resp);
		
	}
}
