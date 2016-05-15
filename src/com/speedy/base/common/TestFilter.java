package com.speedy.base.common;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
  
import org.springframework.web.servlet.HandlerInterceptor;  
import org.springframework.web.servlet.ModelAndView;  
  

  
public class TestFilter implements HandlerInterceptor {  
  
    private static final String LOGIN_URL = "/OnlineExam/index.jsp";  
  
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {  
         HttpSession session = req.getSession(true);  
        // 从session 里面获取用户名的信息  
        Object obj = session.getAttribute("personkey");  
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
        if (obj == null || "".equals(obj.toString())) {  
            res.sendRedirect(LOGIN_URL);  
        }  
        return true;  
    }  
  
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3) throws Exception {  
    }  
  
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3) throws Exception {  
    }  
  
}  