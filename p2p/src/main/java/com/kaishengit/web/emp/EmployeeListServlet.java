package com.kaishengit.web.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Employee;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.util.Page;
import com.kaishengit.web.BaseServlet;
@WebServlet("/emp/list")
public class EmployeeListServlet extends BaseServlet{
	EmployeeService empService = new EmployeeService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		if(StringUtils.isNotEmpty(value)){
			value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
		}
		
		String page = req.getParameter("page");
		int currentPageNo = 1;
		
		if(StringUtils.isNumeric(page)) {
			currentPageNo = Integer.parseInt(page);
		}
		Page<Employee> resPage = empService.findEmployeePage(key,value,currentPageNo);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		req.setAttribute("page", resPage);
		forward("emp/employeeList", req, resp);
	}
	
	
}
