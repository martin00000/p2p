package com.eloan.base.service.impl;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.domain.Logininfo;
import com.eloan.base.mapper.LogininfoMapper;
import com.eloan.base.service.ILoginService;
import com.eloan.base.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dingying
 * @create 2016-12-25 22:32
 **/
@Service
public class ILoginServiceImpl implements ILoginService {

    @Autowired
    private LogininfoMapper logininfoMapper;

    @Override
    public void register(String username, String password) {
        int count = logininfoMapper.selectCountByUserName(username);
        // 用户不存在
        if (count <= 0){
            Logininfo logininfo = new Logininfo();
            logininfo.setUsername(username);
            logininfo.setPassword(MD5.encode(password));
            logininfo.setState(Logininfo.STATE_NORMAL);
            logininfoMapper.insert(logininfo);
        } else {
            new LogicException("用户名已经存在");
        }

    }
}
