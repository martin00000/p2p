package com.eloan.uiweb.web;

import com.eloan.base.annotation.RequireLogin;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Realauth;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.service.IRealAuthService;
import com.eloan.business.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 实名认证
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2017-01-03 下午 4:01
 **/
@Controller
public class RealAuthController extends BaseController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IRealAuthService realAuthService;

    @RequireLogin
    @RequestMapping("realAuth")
    public String realAuthIndex(Model model) {
        Userinfo userinfo = userInfoService.getUserInfo(UserContext.getLogininfo().getId());
        if (userinfo.getRealAuth()) {
            Realauth realAuth = realAuthService.getRealAuth(userinfo.getRealauthId());
            model.addAttribute("realAuth", realAuth);
            return "realAuth_result";
        } else if (userinfo.getRealauthId() != null) {
            model.addAttribute("auditing", true);
            return "realAuth_result";
        } else {
            return "realAuth";
        }
    }

    @RequestMapping("realAuthUpload")
    @ResponseBody
    public String realAuthUpload(MultipartFile file, HttpServletResponse response){
        String uri = realAuthService.uploadPic(file);
        return uri;
    }

    @RequestMapping(value = "realAuth_save", method = RequestMethod.POST)
    public String realAuthSave(Realauth realauth){
        this.realAuthService.saveRealAuth(realauth);
        return "redirect:realAuth.do";
    }


}
