package com.eloan.mgrtool.web;

import com.eloan.base.query.PageResult;
import com.eloan.base.util.ResultJSON;
import com.eloan.business.query.RealAuthQueryObject;
import com.eloan.business.service.IRealAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 管理实名认证
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2017-01-04 下午 2:05
 **/
@Controller
public class RealAuthController extends BaseController {

    @Autowired
    private IRealAuthService realAuthService;

    @RequestMapping("realAuth")
    public String realAuth(RealAuthQueryObject qo, Model model) {
        PageResult pageResult = realAuthService.listRealauth(qo);
        model.addAttribute("pageResult", pageResult);
        return "realAuth/list";
    }

    @RequestMapping("/realAuth_audit")
    @ResponseBody
    public ResultJSON audit(Long id, String remark, Integer state) {
        ResultJSON json = new ResultJSON();
        try {
            this.realAuthService.audit(id, remark, state);
            json.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }
}
