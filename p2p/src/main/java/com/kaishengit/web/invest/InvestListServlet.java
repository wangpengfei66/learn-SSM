package com.kaishengit.web.invest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Invest;
import com.kaishengit.service.InvestService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/invest/list")
public class InvestListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	InvestService invService = new InvestService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageNo = req.getParameter("page");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		value = StringUtils.isoToUtf(value);
		
		int currentPageNo = 1;
		if(StringUtils.isNumeric(pageNo)) {
			currentPageNo = Integer.parseInt(pageNo);
		}
		Page<Invest> page = invService.getPag(currentPageNo,key,value);
		req.setAttribute("page", page);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		forward("invest/investList", req, resp);
	}
}
