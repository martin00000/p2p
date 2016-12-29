package com.eloan.uiweb.interceptor;

import com.eloan.base.annotation.RequireLogin;
import com.eloan.base.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 根据方法上的注解对用户访问的拦截
 *
 * @author Dingying
 * @create 2016-12-29 22:00
 **/
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object
            handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            // 获取方法上是否有requirelogin注解
            RequireLogin rl = hm.getMethodAnnotation(RequireLogin.class);
            if (rl != null) {
                // 用户没有登录
                if (request.getSession().getAttribute(UserContext.LOGININFO_IN_SESSION)
                        == null) {
                    response.sendRedirect("/login.html");
                    return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
}
