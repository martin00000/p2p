package com.eloan.mgrtool.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 跳转首页
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 下午 4:47
 **/
@Controller
public class IndexController extends BaseController {

    @RequestMapping("index")
    public String index() {
        return "main";
    }
}
