package com.eloan.uiweb.web;

import com.eloan.base.annotation.RequireLogin;
import com.eloan.base.domain.Logininfo;
import com.eloan.base.service.ISystemDictionaryService;
import com.eloan.base.util.ResultJSON;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2017-01-03 上午 10:04
 **/
@Controller
public class BaseInfoController extends BaseController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @RequireLogin
    @RequestMapping("basicInfo")
    public String baseInfoIndex(Model model) {
        Logininfo logininfo = UserContext.getLogininfo();
        Userinfo userInfo = userInfoService.getUserInfo(logininfo.getId());
        model.addAttribute("userinfo", userInfo);
        model.addAttribute("incomeGrade", systemDictionaryService.listOption("incomeGrade"));
        model.addAttribute("marriage", systemDictionaryService.listOption("marriage"));
        model.addAttribute("kidCount", systemDictionaryService.listOption("kidCount"));
        model.addAttribute("educationBackground", systemDictionaryService.listOption("educationBackground"));
        model.addAttribute("houseCondition", systemDictionaryService.listOption("houseCondition"));
        return "userInfo";
    }

    @RequireLogin
    @RequestMapping("basicInfo_save")
    @ResponseBody
    public ResultJSON basicInfoSave(Userinfo userinfo){
        this.userInfoService.updateBasicInfo(userinfo);
        return new ResultJSON(true);
    }
}
