package com.kaishengit.web.invest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Interest;
import com.kaishengit.service.InterestService;
import com.kaishengit.util.Page;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/inte/list")
public class InterestListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	InterestService inService = new InterestService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		value = StringUtils.isoToUtf(value);
		String p = req.getParameter("page");
		int currentPageNo = 1;
		if(StringUtils.isNumeric(p)) {
			currentPageNo = Integer.parseInt(p);
		}
		
		Page<Interest> page = inService.getPage(key,value,currentPageNo);
		
		req.setAttribute("page", page);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		forward("invest/interestList", req, resp);
	}
}
