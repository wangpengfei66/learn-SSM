package com.kaishengit.web.emp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.kaishengit.entity.Employee;
import com.kaishengit.entity.Result;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.util.AESUtils;
import com.kaishengit.web.BaseServlet;
@WebServlet("/login")
public class LoginServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	EmployeeService empService = new EmployeeService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		forward("emp/login", req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//TODO
		//只支持电话号码登录
		Result res = null;
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String callback = req.getParameter("callback");
		String rememberMe = req.getParameter("rememberMe");
		String ip = req.getRemoteAddr();
		try{
			Employee emp = empService.login(userName,password,ip);
			//保存cookie
			if(StringUtils.isNotEmpty(rememberMe)) {
				String userPass = userName + "-" + password;
				String userToken = AESUtils.encode(userPass);
				
				Cookie cookie = new Cookie("userToken", userToken);
				cookie.setDomain("localhost");
				cookie.setPath("/");
				cookie.setMaxAge(60* 60 *24 * 7);
				cookie.setHttpOnly(true);
				resp.addCookie(cookie);
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("emp", emp);
			
			res = new Result("success");
			res.setData(callback);
		}catch(ServiceException e){
			res = new Result("error",e.getMessage());
		}
		sendJson(res,resp);
	}
	
}
