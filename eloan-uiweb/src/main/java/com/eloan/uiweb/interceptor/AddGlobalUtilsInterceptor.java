package com.eloan.uiweb.interceptor;

import com.eloan.uiweb.web.SystemDictionaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 将工具类放到model中
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2017-01-03 下午 2:16
 **/
public class AddGlobalUtilsInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ApplicationContext ctx;

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        Map<String, Object> utils = new HashMap<String, Object>();
        Assert.notNull(ctx);
        utils.put("_SDUtil", ctx.getBean(SystemDictionaryUtil.class));
        if (modelAndView != null)
            modelAndView.addObject("_GUtils", utils);
        super.postHandle(request, response, handler, modelAndView);
    }

}
