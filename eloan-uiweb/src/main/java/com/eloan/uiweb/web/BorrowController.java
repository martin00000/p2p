package com.eloan.uiweb.web;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.service.IAccountService;
import com.eloan.business.service.IUserInfoService;
import com.eloan.business.util.BidConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 借款
 *
 * @author Dingying
 * @create 2017-01-02 14:29
 **/
@Controller
public class BorrowController extends BaseController {

    @Autowired
    IUserInfoService userInfoService;

    @Autowired
    IAccountService accountService;

    @RequestMapping("borrow")
    public String borrowIndex(Model model) {
        Logininfo logininfo = UserContext.getLogininfo();
        if (logininfo == null){
            return "redirect:borrow.html";
        }
        Userinfo userinfo = userInfoService.getUserInfo(logininfo.getId());
        Account account = accountService.getAccount(logininfo.getId());
        model.addAttribute("userinfo", userinfo);
        model.addAttribute("account", account);
        model.addAttribute("creditBorrowScore", BidConst.CREDIT_BORROW_SCORE);
        return "borrow";
    }
}
