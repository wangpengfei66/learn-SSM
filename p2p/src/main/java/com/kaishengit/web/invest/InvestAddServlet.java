package com.kaishengit.web.invest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Project;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.InvestService;
import com.kaishengit.service.ProjectService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/invest/add")
public class InvestAddServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CustomerService custService = new CustomerService();
	ProjectService proService = new ProjectService();
	InvestService inveService = new InvestService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查询出用户列表和项目列表，并返回前端界面
		String proId = req.getParameter("proId");
		List<Customer> custList = custService.findAllCust();
		List<Project> proList = proService.findAllPro();
		
		req.setAttribute("proId", proId);
		req.setAttribute("proList", proList);
 		req.setAttribute("custList", custList);
		forward("invest/investAdd", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		
		String custId = req.getParameter("cust");
		String proId = req.getParameter("pro");
		String restMoney = req.getParameter("restMoney");
		String investMoney = req.getParameter("investMoney");
		String rate = req.getParameter("rate");
		String month = req.getParameter("month");
		String signDate = req.getParameter("signDate");
		String endDate = req.getParameter("endDate");
		int empId = getEmpId(req);
		try{
			inveService.addInvest(Integer.parseInt(custId),Integer.parseInt(proId),empId,Double.parseDouble(restMoney),Double.parseDouble(investMoney),Double.parseDouble(rate),Integer.parseInt(month),signDate,endDate);
			res = new Result("success");
		}catch(ServiceException e){
			res = new Result("error",e.getMessage());
		}
		sendJson(res, resp);
	}
}
