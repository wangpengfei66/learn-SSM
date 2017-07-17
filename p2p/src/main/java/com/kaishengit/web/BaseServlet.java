package com.kaishengit.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kaishengit.entity.Employee;

public class BaseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	//提取出公共的请求转发的方法
	public void forward(String url,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/WEB-INF/views/" + url + ".jsp").forward(req, resp);
	}
	
	public void sendJson(Object obj,HttpServletResponse resp) throws IOException{
		resp.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print(new Gson().toJson(obj));
		out.flush();
		out.close();
	}
	
	public void sendText(Object text,HttpServletResponse resp) throws IOException{
		resp.setContentType("text/plain;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		out.print(new Gson().toJson(text));
		out.flush();
		out.close();
	}
	
	public int getEmpId(HttpServletRequest req){
		HttpSession session = req.getSession();
		Employee emp = (Employee) session.getAttribute("emp");
		int id = emp.getId();
		return id;
	}
	
}
