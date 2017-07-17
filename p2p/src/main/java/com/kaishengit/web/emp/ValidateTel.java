package com.kaishengit.web.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.EmployeeService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/validate/tel")
public class ValidateTel extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	EmployeeService empService = new EmployeeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tel = req.getParameter("tel");
		boolean res = empService.validateTel(tel);
		sendText(res,resp);
	}
	
	
}
