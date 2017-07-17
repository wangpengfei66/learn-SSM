package com.kaishengit.web.com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Result;
import com.kaishengit.service.CompanyService;
import com.kaishengit.web.BaseServlet;
@WebServlet("/com/del")
public class CompanyDeletServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	CompanyService comService = new CompanyService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Result res = null;
		
		if(StringUtils.isNumeric(id)) {
			comService.delById(Integer.parseInt(id));
			res = new Result("success");
		}else{
			res = new Result("error","系统异常");
		}
		 sendJson(res, resp);
	}
	
}
