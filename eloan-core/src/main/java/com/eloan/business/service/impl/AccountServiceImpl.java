package com.eloan.business.service.impl;

import com.eloan.business.domain.Account;
import com.eloan.business.mapper.AccountMapper;
import com.eloan.business.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 上午 10:39
 **/
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getAccount(long id) {
        return accountMapper.selectByPrimaryKey(id);
    }
}
