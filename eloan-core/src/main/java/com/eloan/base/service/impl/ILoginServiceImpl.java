package com.eloan.base.service.impl;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.domain.IpLog;
import com.eloan.base.domain.Logininfo;
import com.eloan.base.mapper.IpLogMapper;
import com.eloan.base.mapper.LogininfoMapper;
import com.eloan.base.service.ILoginService;
import com.eloan.base.util.MD5;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Account;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.AccountMapper;
import com.eloan.business.mapper.UserinfoMapper;
import com.eloan.business.util.BidConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IpLogMapper ipLogMapper;

    @Override
    @Transactional
    public void register(String username, String password)
            throws LogicException {
        int count = logininfoMapper.selectCountByUserName(username,Logininfo.USERTYPE_NORMAL);
        // 用户不存在
        if (count <= 0) {
            // 创建一个logininfo对象
            Logininfo logininfo = new Logininfo();
            logininfo.setUsername(username);
            logininfo.setPassword(MD5.encode(password));
            logininfo.setState(Logininfo.STATE_NORMAL);
            logininfo.setUserType(Logininfo.USERTYPE_NORMAL);
            logininfoMapper.insert(logininfo);

            // 创建用户相关的账户信息
            Account account = Account.empty(logininfo.getId());
//            account.setTradePassword(logininfo.getPassword());
            accountMapper.insert(account);

            // 创建用户相关的用户信息
            Userinfo userinfo = Userinfo.empty(logininfo.getId());
            userinfoMapper.insert(userinfo);
        } else {
            new LogicException("用户名已经存在");
        }

    }

    @Override
    public Boolean CheckUsername(String username, Integer userType) {
        int count = logininfoMapper.selectCountByUserName(username, userType);
        if (count > 0) {
            return false;
        }
        return true;
    }

    @Override
    public Logininfo login(String username, String password, Integer userType, String ip) {
        password = MD5.encode(password);
        IpLog log = new IpLog(ip, IpLog.LOGIN_FAIL, username, null, userType);
        Logininfo logininfo = logininfoMapper.selectByUsernameAndPassword(username, password, userType);
        // 将logininfo对象放到session中
        if (logininfo != null) {
            UserContext.setLogininfo(logininfo);
            log.setLoginInfoId(logininfo.getId());
            // 讲登录状态改为成功
            log.setLoginState(IpLog.LOGIN_SUCCESS);
        }
        ipLogMapper.insert(log);
        return logininfo;
    }

    @Override
    public boolean hasAdminUser() {
        int count = logininfoMapper.selectCountByUserType(Logininfo.USERTYPE_SYSTEM);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void creatDefaultAdminUser() {
        Logininfo logininfo = new Logininfo();
        logininfo.setUsername(BidConst.DEFAULT_ADMIN_NAME);
        logininfo.setPassword(MD5.encode(BidConst.DEFAULT_ADMIN_PASSWORD));
        logininfo.setUserType(Logininfo.USERTYPE_SYSTEM);
        logininfo.setState(Logininfo.STATE_NORMAL);
        logininfo.setAdmin(true);// 是超级管理员
        logininfoMapper.insert(logininfo);
    }
}
