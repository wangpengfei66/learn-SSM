package com.kaishengit.crm.controller.interceptor;

import com.kaishengit.crm.entity.Account;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter{
    /**
     * 登录验证的拦截器
     * @param request
     * @param response
     * @param handler
     * @return true or false
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取url
        String requestUrl = request.getRequestURI();
        //除了斜杠（/）之外都要判断是否登录
        if(requestUrl.startsWith("/static")) {
            return true;
        }
        if("/".equals(requestUrl)) {
            return true;
        }else {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("currentUser");
            if(account == null) {
                response.sendRedirect("/");
                return false;
            }else{
                return true;
            }
        }
    }
}
