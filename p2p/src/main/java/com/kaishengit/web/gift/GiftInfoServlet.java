package com.kaishengit.web.gift;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Customer;
import com.kaishengit.entity.Gift;
import com.kaishengit.entity.Result;
import com.kaishengit.service.GiftService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/gift/info")
public class GiftInfoServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	GiftService giftService = new GiftService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Result res = null;
		String giftId = req.getParameter("id");
		if(StringUtils.isNumeric(giftId)) {
			Gift gift = giftService.findById(giftId);
			res = new Result("success",gift);
		}else{
			res = new Result("error","参数错误");
		}
		sendJson(res, resp);
	}
	
}
