package com.eloan.mgrtool.web;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.domain.Logininfo;
import com.eloan.base.service.ILoginService;
import com.eloan.base.util.ResultJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台系统登录
 *
 * @author Dingying
 * @create 2016-12-27 23:03
 **/
@Controller
public class LoginController extends BaseController {

    @Autowired
    private ILoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ResultJSON login(String username, String password) {
        ResultJSON json = new ResultJSON(true);
        try {
            Logininfo logininfo = loginService.login(username, password, Logininfo.USERTYPE_SYSTEM);
            if(logininfo == null)
                throw new LogicException("用户名密码错误", -130);
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return json;
    }




}