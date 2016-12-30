package com.eloan.uiweb.web;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.annotation.RequireLogin;
import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.ISendVerifyCodeService;
import com.eloan.business.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 个人中心
 *
 * @author Dingying
 * @create 2016-12-28 23:50
 **/
@Controller
public class PersonalController extends BaseController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ISendVerifyCodeService sendVerifyCodeService;

    @RequireLogin
    @RequestMapping("/personal")
    public String toPage(Model model) {
        Logininfo logininfo = UserContext.getLogininfo();
        if (logininfo != null) {
            model.addAttribute("userinfo", userInfoService.getUserInfo(logininfo.getId()));
            model.addAttribute("account", accountService.getAccount(logininfo.getId()));
        } else {
            return "index";
        }
        return "personal";
    }

    @RequestMapping("/sendVerifyCode")
    @ResponseBody
    public ResultJSON sendVerifyCode(Model model, String phoneNumber) {
        ResultJSON resultJSON = new ResultJSON();
        try {
            sendVerifyCodeService.sendVerifyCode(phoneNumber);
            resultJSON.setSuccess(true);
        } catch (LogicException e) {
            resultJSON.setSuccess(false);
            resultJSON.setMsg(e.getMessage());
        }
        return resultJSON;
    }

    @RequestMapping("/bindPhone")
    @ResponseBody
    public ResultJSON bindPhone(String phoneNumber,String verifyCode, Model model) {
        ResultJSON json = new ResultJSON(true);
        try {
            this.userInfoService.bindPhone(phoneNumber,verifyCode);
            json.setSuccess(true);
        } catch (LogicException le) {
            json.setSuccess(false);
            json.setMsg(le.getMessage());
        }
        return json;
    }

    @RequestMapping("/bindEmail")
    @ResponseBody
    public ResultJSON sendBindEmail(String email, Model model) {
        ResultJSON json = new ResultJSON();
        try {
            this.userInfoService.sendBindEmail(email);
            json.setSuccess(true);
        } catch (LogicException le) {
            json.setSuccess(false);
            json.setMsg(le.getMessage());
        }
        return json;
    }

    @RequestMapping("/mailBindVerify")
    public String mailBindVerify(String checkCode, Model model) {
        try {
            this.userInfoService.bindMailVerify(checkCode);
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
        }
        return "bindResult";
    }


}
