package com.eloan.mgrtool.web;

import com.eloan.base.query.IpLogQueryObject;
import com.eloan.base.service.IpLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 后台查看用户日志
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 下午 5:09
 **/
@Controller
public class IpLogController extends BaseController {

    @Autowired
    private IpLogService ipLogService;

    @RequestMapping("iplog")
    public String getIpLog(IpLogQueryObject qo, Model model) {
        if (StringUtils.isBlank(qo.getUsername()))
            qo.setUsername(null);
        model.addAttribute("pageResult", ipLogService.getIpLog(qo));
        return "ipLog/list";
    }

}
