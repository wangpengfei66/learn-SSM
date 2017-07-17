package com.kaishengit.web.gift;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Gift;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.GiftSendQueryService;
import com.kaishengit.service.GiftService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/gift/send")
public class GiftSendServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	GiftSendQueryService giftSendService = new GiftSendQueryService();
	GiftService giftService = new GiftService();
	CustomerService custService = new CustomerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//传一些礼品的默认信息，所需积分等，展示在输入框中
		String giftId = req.getParameter("giftId");
		//获取礼品列表
		List<Gift> giftList = giftService.findAll();
		//获取客户列表
		List<Customer> custList = custService.findAllCust();
		
		req.setAttribute("giftId", giftId);
		req.setAttribute("custList", custList);
		req.setAttribute("giftList", giftList);
		forward("gift/giftSend", req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		//获取三个id存到数据库中，将客户的积分减去，将礼品的库存减去
		String custId = req.getParameter("cust");
		String giftId = req.getParameter("gift");
		String point = req.getParameter("needPoint");
		
		int empId = getEmpId(req);
		try{
			giftSendService.add(Integer.parseInt(custId),Integer.parseInt(giftId),empId);
			
			custService.updatePoint(Integer.parseInt(point),Integer.parseInt(custId));
		
			giftService.update(Integer.parseInt(giftId));
			res = new Result("success");
		}catch(ServiceException e) {
			res = new Result("error",e.getMessage());
		}
		sendJson(res, resp);
	}
}
