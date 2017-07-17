package com.kaishengit.web.emp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Company;
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CompanyService;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/emp/edit")
public class EmployeeEditServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	EmployeeService empService = new EmployeeService();
	
/*	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		System.out.println(id);
	
	}*/
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Result res = null;
		String companyId = req.getParameter("company");
		String deptName = req.getParameter("dept");
		String state = req.getParameter("state");
		try{
			empService.editEmployeeById(id,state,companyId,deptName);
			res = new Result("success");
		}catch(ServiceException e) {
			res = new Result("error", e.getMessage());
		}
		sendJson(res, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		CompanyService comService = new CompanyService();
		if(StringUtils.isNumeric(id)) {			
			Employee emp = empService.findById(Integer.parseInt(id));
			if(emp == null) {
				resp.sendError(404,"参数异常");
			}else{				
				List<Company> comList = comService.getCompanyList();
				List<String> deptNameList = comService.getDeptNameList();
				
				req.setAttribute("comList", comList);
				req.setAttribute("deptList", deptNameList);
				req.setAttribute("emp", emp);
				forward("emp/employeeEdit", req, resp);
			}
		}else{
			resp.sendError(404,"参数异常");
		}
	}

}
