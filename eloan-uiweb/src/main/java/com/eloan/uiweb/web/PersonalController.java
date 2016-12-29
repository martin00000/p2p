package com.eloan.uiweb.web;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.UserContext;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
