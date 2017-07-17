package com.kaishengit.web.gift;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.GiftService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/gift/add")
public class GiftAddServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	GiftService giftService = new GiftService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		forward("gift/giftAdd", req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		String giftName = req.getParameter("giftName");
		String num = req.getParameter("num");
		String point = req.getParameter("point");
		String price = req.getParameter("price");
		String remark = req.getParameter("remark");
		try{
			giftService.saveGift(giftName,num,point,price,remark);
			res = new Result("success");
		}catch(ServiceException e) {
			res = new Result("error",e.getMessage());
		}
		sendJson(res, resp);
	}
}
