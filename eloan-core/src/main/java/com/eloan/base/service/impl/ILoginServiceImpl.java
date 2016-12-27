package com.eloan.base.service.impl;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.domain.Logininfo;
import com.eloan.base.mapper.LogininfoMapper;
import com.eloan.base.service.ILoginService;
import com.eloan.base.util.MD5;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.AccountMapper;
import com.eloan.business.mapper.UserinfoMapper;
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

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public void register(String username, String password)
            throws LogicException {
        int count = logininfoMapper.selectCountByUserName(username);
        // 用户不存在
        if (count <= 0) {
            // 创建一个logininfo对象
            Logininfo logininfo = new Logininfo();
            logininfo.setUsername(username);
            logininfo.setPassword(MD5.encode(password));
            logininfo.setState(Logininfo.STATE_NORMAL);
            logininfoMapper.insert(logininfo);

            // 创建用户相关的账户信息
            Account account = Account.empty(logininfo.getId());
            accountMapper.insert(account);

            // 创建用户相关的用户信息
            Userinfo userinfo = Userinfo.empty(logininfo.getId());
            userinfoMapper.insert(userinfo);

        } else {
            new LogicException("用户名已经存在");
        }

    }

    @Override
    public Boolean CheckUsername(String username) {
        int count = logininfoMapper.selectCountByUserName(username);
        if (count > 0) {
            return false;
        }
        return true;
    }

    @Override
    public Logininfo login(String username, String password) {
        password = MD5.encode(password);
        Logininfo logininfo = logininfoMapper.selectByUsernameAndPassword(username, password);
        // 将logininfo对象放到session中
        if (logininfo == null)
            UserContext.setLogininfo(logininfo);
        return logininfo;
    }
}
