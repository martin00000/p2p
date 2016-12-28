package com.eloan.uiweb.web;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.UserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跳转页面
 *
 * @author Dingying
 * @create 2016-12-28 23:50
 **/
@Controller
public class PageController extends BaseController {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @RequestMapping("/personal")
    public String toPage(Model model, HttpServletResponse response) {
        Logininfo logininfo = UserContext.getLogininfo();
        if (logininfo != null) {
            Userinfo userinfo = userinfoMapper.selectByPrimaryKey(logininfo.getId());
            model.addAttribute("userinfo", userinfo);
        } else {
            try {
                response.sendRedirect("/register.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "personal";
    }


}
