package com.kaishengit.web.fliter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.collect.Maps;
import com.kaishengit.entity.Employee;

public class PrivilegeFilter extends AbstractFliter{
	private static Map<String, String> maps = Maps.newHashMap();
	static{
		maps.put("/cust", "销售部,经理办公室");
		maps.put("/invest", "销售部,经理办公室");
		maps.put("/gift", "运营部,经理办公室" );
		maps.put("/sal", "财务部,经理办公室");
		maps.put("/pro", "经理办公室");
		maps.put("/emp", "人事部,经理办公室");
		maps.put("/com", "经理办公室");
	}
	
	List<String> lists = null;
	Set<String> sets = null; 
	
	
	@Override
	public void init(FilterConfig Config) throws ServletException {
		String validateUrl = Config.getInitParameter("privUrl");
		lists = Arrays.asList(validateUrl.split(","));
		sets = maps.keySet();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		//获取uri，判断uri是否直接通过
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("emp");
		
		if(validate(uri)) {
			//判断当前登录的帐号用没有权限
			if(privilegeValidate(uri,emp)) {
				filterChain.doFilter(req, resp);
			}else{
				resp.sendError(403);
			}
			
		} else{
			filterChain.doFilter(req, resp);
		}
	}

	private boolean privilegeValidate(String uri, Employee emp) {
		//判断uri是否以maps中的某个key开头
		for(String set :sets) {
			if(uri.startsWith(set)) {
				//判断session中的emp的deptName是否等于maps的当前key的value值
				String deptName = emp.getDeptName();
				String[] depts = maps.get(set).split(",");
				for(String dept : depts) {
					if(dept.equals(deptName)) {
						//当且仅当当前session中的deptName和maps中的对应的value值相等的时候返回true
						return true;
					}
				}
			}
			
		}
		
		return false;
	}
	
	
	/**
	 * 验证Url是否符合
	 * @param uri
	 * @return
	 */
	private boolean validate(String uri) {
		if(lists == null) {
			return false;
		}
		
		if(lists.contains(uri)){
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
