package com.kaishengit.web.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/emp/del")
public class EmployeeDeletServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	EmployeeService empService = new EmployeeService();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		//System.out.println("id" + id);
		Result res = null;
		
		if(StringUtils.isNumeric(id)) {			
			try{
				empService.delEmployeeById(Integer.parseInt(id));
				res = new Result("success");
			}catch(ServiceException e) {
				res = new Result("error", e.getMessage());
			}
		}else{
			res = new Result("error", "参数异常");
		}
		sendJson(res,resp);
	}

}
