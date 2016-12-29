package com.eloan.uiweb.web;

import com.eloan.base.query.IpLogQueryObject;
import com.eloan.base.service.IpLogService;
import com.eloan.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 前台用户查看登录记录
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 下午 2:43
 **/
@Controller
public class IpLogController extends BaseController {

    @Autowired
    private IpLogService ipLogService;

    @RequestMapping(value = "/iplog")
    public String getIpLog(IpLogQueryObject qo, Model model) {
        qo.setUsername(UserContext.getLogininfo().getUsername());
        model.addAttribute("pageResult", ipLogService.getIpLog(qo));
        return "iplog_list";
    }

}
