package com.eloan.uiweb.web;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.service.ILoginService;
import com.eloan.base.util.ResultJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 注册
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-26 下午 3:02
 **/
@Controller
public class RejisterController extends BaseController {

    @Autowired
    private ILoginService iLoginService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ResultJSON register(String username, String password) {
        try {
            iLoginService.register(username, password);
            return new ResultJSON(true);
        } catch (LogicException e) {
            e.printStackTrace();
            return new ResultJSON(false, e.getMessage());
        }
    }

    @RequestMapping(value = "checkUsername", method = RequestMethod.POST)
    @ResponseBody
    public ResultJSON checkUsername(String username) {
        ResultJSON json = new ResultJSON();
        json.setSuccess(iLoginService.CheckUsername(username));
        return json;
    }

}
