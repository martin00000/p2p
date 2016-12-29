package com.eloan.business.service.impl;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.util.DateUtil;
import com.eloan.base.util.HttpClientUtil;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.VerifyCode;
import com.eloan.business.service.ISendVerifyCodeService;
import com.sun.tools.javac.jvm.ByteCodes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import static com.sun.tools.javac.jvm.ByteCodes.ret;

@Service
public class SendVerifyCodeService implements ISendVerifyCodeService {

    @Value("${m5c.url}")
    private String url;

    @Value("${m5c.username}")
    private String username;

    @Value("${m5c.password}")
    private String password;

    @Value("${m5c.apikey}")
    private String apikey;

    @Override
    public boolean verifyCode(String phoneNumber, String verifyCode) {
        VerifyCode vc = UserContext.getVerifyCode();
        return vc != null//发过短信;
                && vc.getPhoneNumber().equals(phoneNumber)
                && vc.getRandomCode().equals(verifyCode)
                && DateUtil.getSecondsBetweenDates(vc.getLastSendTime(), new Date()) <= 60 * 3;
    }

    @Override
    public void sendVerifyCode(String phoneNumber) {
        if (checkUserCanSendVerifyCode()) {
            String randomCode = UUID.randomUUID().toString().substring(0, 4);
            StringBuilder sb = new StringBuilder(100).append("您的手机验证码为:")
                    .append(randomCode).append(",请在3分钟之内输入有效!");

            HashMap<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);
            params.put("apikey", apikey);
            params.put("mobile", phoneNumber.toString());
            params.put("content", sb.toString());
            String ret = HttpClientUtil.doPost(url, params);
            if ("success" .equals(ret.substring(0, ret.indexOf(":")))) {
                VerifyCode vc = new VerifyCode(phoneNumber, randomCode, new Date(),
                        sb.toString());
                UserContext.setVerifyCode(vc);
            } else {
                throw new LogicException("短信发送失败，请联系平台管理员！");
            }
        } else {
            throw new RuntimeException("你发送短信的频率太高");
        }
    }

    private boolean checkUserCanSendVerifyCode() {
        VerifyCode vc = UserContext.getVerifyCode();
        return vc == null
                || (vc != null && DateUtil.getSecondsBetweenDates(
                vc.getLastSendTime(), new Date()) > 120L);
    }

}
