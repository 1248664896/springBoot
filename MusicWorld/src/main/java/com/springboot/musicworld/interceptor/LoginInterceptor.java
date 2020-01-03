package com.springboot.musicworld.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoginInterceptor implements HandlerInterceptor {


    public  static Set<String> URL ;
    //静态代码块
    static {
        URL = new HashSet<String>();
        URL.add("/");
    }




    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();//URL.contains(url)
        if(url.startsWith("/admin")&&null==request.getSession().getAttribute("admin")&&request.getSession().getAttribute("users")==null){
            request.getSession().setAttribute("errorMsg", "请登陆");
            response.sendRedirect(request.getContextPath() + "/admin/login");
             return true;
        }else{
            request.getSession().removeAttribute("errorMsg");
            return true;
        }

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
