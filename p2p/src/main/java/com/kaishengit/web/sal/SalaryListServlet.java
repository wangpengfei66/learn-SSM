package com.kaishengit.web.sal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Employee;
import com.kaishengit.service.SalaryService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/sal/list")
public class SalaryListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	SalaryService salService = new SalaryService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String key = req.getParameter("key");
		String value = req.getParameter("value");
		String p = req.getParameter("page");
		value = StringUtils.isoToUtf(value);
		int currentPageNo = 1;
		if(StringUtils.isNumeric(p)) {
			currentPageNo = Integer.parseInt(p);
		}
		
		Page<Employee> page = salService.getSalaryPage(key,value,currentPageNo);
		req.setAttribute("page", page);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		forward("sal/salList", req, resp);
	}
}
