package com.eloan.mgrtool.listener;

import com.eloan.base.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 初始化管理员账号
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-28 下午 4:44
 **/
@Component
public class AdminUserCreatorListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ILoginService loginService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!loginService.hasAdminUser()) {
            loginService.creatDefaultAdminUser();
        }
    }
}
