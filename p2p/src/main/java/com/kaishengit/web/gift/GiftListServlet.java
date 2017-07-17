package com.kaishengit.web.gift;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Gift;
import com.kaishengit.service.GiftService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/gift/list")
public class GiftListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	GiftService giftService = new GiftService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pageNo = req.getParameter("page");
		int currentPageNo = 1;
		if(StringUtils.isNumeric(pageNo)) {
			currentPageNo = Integer.parseInt(pageNo);
		}
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		value = StringUtils.isoToUtf(value);
		req.setAttribute("key", key);
		
		Page<Gift> resPage = giftService.getPage(key,value,currentPageNo);
		
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		req.setAttribute("page", resPage);
		
		forward("gift/giftList", req, resp);
	}
	
}
