package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.dao.SetDao;
import com.kaishengit.entity.Result;
import com.kaishengit.entity.Setting;
import com.kaishengit.exception.ServiceException;
@WebServlet("/setting")
public class SettingServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	
	SetDao setDao = new SetDao();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Setting set = setDao.findById(1);
		req.setAttribute("set", set);
		forward("/setting", req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		int id = 1;
		//回报率
		String interestRate = req.getParameter("interestRate");
		//佣金率
		String commissionRate = req.getParameter("commissionRate");
		try{
			setDao.update(Double.parseDouble(interestRate),Double.parseDouble(commissionRate),id);
			res = new Result("success");
		}catch(ServiceException e) {
			res = new Result("error",e.getMessage());
		}
		sendJson(res, resp);
	}
}
