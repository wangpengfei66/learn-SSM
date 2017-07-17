package com.kaishengit.web.fliter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Employee;
import com.kaishengit.service.EmployeeService;
import com.kaishengit.util.AESUtils;

public class ValidateFilter extends AbstractFliter{
	List<String> lists = null;
	EmployeeService empService = new EmployeeService();
	@Override
	public void init(FilterConfig Config) throws ServletException {
		String validateUrl = Config.getInitParameter("ValidateUrl");
		lists = Arrays.asList(validateUrl.split(","));
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//获取URI，判断URI是否直接通过
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String uri = req.getRequestURI();
		//1.访问的uri是否需要校验
		if(Validate(uri)) {
			//正则表达式校验  如果通不过校验，则直接send404
			String regix = "(/{1}\\w+)+";
			if(uri.matches(regix)) {
				//2.session中是否存在emp对象
				HttpSession session = req.getSession();
				Employee emp = (Employee) session.getAttribute("emp");
				if(emp == null){
					//3.实现自动登录
					emp = getEmployeeByCookie(req);
					if(emp != null) {
						session.setAttribute("emp", emp);
						chain.doFilter(request, response);
					}else{
						resp.sendRedirect("/login?callback=" + uri);
					}
				}else{
					chain.doFilter(request, response);
				}
				
			}else{
				resp.sendError(404,"地址输入不正确");
			}
		}else{
			//不需要uri校验，直接通过过滤器
			chain.doFilter(request, response);
		}
	}


	private Employee getEmployeeByCookie(HttpServletRequest req) {
		Employee emp = null;
		//获得cookie
		Cookie [] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userToken")) {
					String userToken = cookie.getValue();
					String userPass = AESUtils.decode(userToken);
					String userName = userPass.split("-")[0];
					String password = userPass.split("-")[1];
					String ip = req.getRemoteAddr();
					emp = empService.login(userName, password, ip);
				}
			}
		}
		return emp;
	}

	private boolean Validate(String uri) {
		if(lists == null) {
			return false;
		}
		
		if(lists.contains(uri)) {
			return true;
		}
		
		for(String str : lists) {
			if(uri.startsWith(str)){
				return true;
			}
		}
		return false;
	}

}
