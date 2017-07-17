package com.kaishengit.web.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.service.EmployeeService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/validate/idNo")
public class ValidateIdNo extends BaseServlet{

	private static final long serialVersionUID = 1L;
	EmployeeService empService = new EmployeeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idNo = req.getParameter("idNo");
		boolean res = empService.validateIdNo(idNo);
		sendText(res,resp);
	}
}
