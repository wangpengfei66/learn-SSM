package com.kaishengit.web.com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Company;
import com.kaishengit.service.CompanyService;
import com.kaishengit.util.Page;
import com.kaishengit.web.BaseServlet;
@WebServlet("/com/list")
public class CompanyListServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CompanyService comService = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		
		if(StringUtils.isNotEmpty(value)) {
			value = new String(value.getBytes("ISO8859-1"),"UTF-8");
		}
		String page = req.getParameter("page");
		int currentPageNo = 1;
		if(StringUtils.isNumeric(page)) {
			currentPageNo = Integer.parseInt(page);
		}
		Page<Company> resPage = comService.getCompanyPage(key,value,currentPageNo);
		req.setAttribute("key", key);
		req.setAttribute("value", value);
		req.setAttribute("page", resPage);
		
		forward("com/companyList", req, resp);
	}
	
}
