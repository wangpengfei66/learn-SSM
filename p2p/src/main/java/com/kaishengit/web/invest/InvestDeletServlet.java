package com.kaishengit.web.invest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.InvestService;
import com.kaishengit.util.StringUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/invest/del")
public class InvestDeletServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	InvestService invService = new InvestService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Result res = null;
		String id = req.getParameter("id");
		if(StringUtils.isNumeric(id)) {
			try{
				invService.delInvest(Integer.parseInt(id));
				res = new Result("success");
			}catch(ServiceException e) {
				res = new Result("error",e.getMessage());
			}
		}else{
			res = new Result("error","参数错误");
		}
		sendJson(res, resp);
	}
}
