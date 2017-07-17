package com.kaishengit.web.cust;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

@WebServlet("/cust/list")
public class CustomerListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	CustomerService custService = new CustomerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1.获取搜索的键和值，根据键和值获取对应的page，page中含有list，就是这么简单，把page传回前端界面
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		if(StringUtils.isNotEmpty(value)) {
			value = StringUtils.isoToUtf(value);
		}
		String page = req.getParameter("page");
		
		int currentPageNo = 1;
		if(StringUtils.isNumeric(page)) {
			currentPageNo = Integer.parseInt(page);
		}
		Page<Customer> resPage = custService.findPage(key,value,currentPageNo);
		req.setAttribute("page", resPage);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		forward("cust/custList", req, resp);
	}
	
}
