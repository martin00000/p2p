package com.eloan.base.util;

import com.eloan.base.domain.Logininfo;
import com.eloan.business.domain.VerifyCode;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户上下文对象用于从session中获取当前登录的用户
 * @author Dingying
 * @create 2016-12-27 23:27
 **/
public class UserContext {

    public static final String LOGININFO_IN_SESSION = "logininfo";

    public static final String VERIFYCODE_IN_SESSION = "verifycode";

    private static HttpServletRequest getRequest() {
      return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static Logininfo getLogininfo() {
        return (Logininfo) getRequest().getSession().getAttribute(LOGININFO_IN_SESSION);
    }

    public static void setLogininfo(Logininfo logininfo) {
        getRequest().getSession().setAttribute(LOGININFO_IN_SESSION, logininfo);
    }

    public static void setVerifyCode(VerifyCode vc) {
        getRequest().getSession().setAttribute(VERIFYCODE_IN_SESSION, vc);
    }
    public static VerifyCode getVerifyCode() {
        return
                (VerifyCode)getRequest().getSession().getAttribute(VERIFYCODE_IN_SESSION);
    }
}
