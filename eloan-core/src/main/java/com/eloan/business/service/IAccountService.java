package com.eloan.business.service;

import com.eloan.business.domain.Account;

/**
 * <p>
 * 用户账号信息
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 上午 10:15
 **/
public interface IAccountService {
    /**
     * 根据id获取用户账户
     * @param id
     * @return
     */
    Account getAccount(long id);
}
