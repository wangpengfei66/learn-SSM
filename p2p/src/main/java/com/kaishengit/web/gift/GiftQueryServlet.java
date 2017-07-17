package com.kaishengit.web.gift;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.GiftSend;
import com.kaishengit.service.GiftSendQueryService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;

@WebServlet("/gift/send/query")
public class GiftQueryServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	GiftSendQueryService giftSendQueryService = new GiftSendQueryService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		String p = req.getParameter("page");
		int currentPageNo = 1;
		value = StringUtils.isoToUtf(value);
		if(StringUtils.isNumeric(p)) {
			currentPageNo = Integer.parseInt(p);
		}
		Page<GiftSend> page = giftSendQueryService.getPage(key,value,currentPageNo);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		req.setAttribute("page", page);
		
		forward("gift/giftSendQuery", req, resp);
	}
	
	
}
