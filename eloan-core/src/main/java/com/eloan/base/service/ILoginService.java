package com.eloan.base.service;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.domain.Logininfo;

/**
 * 登录注册
 */
public interface ILoginService {

    /**
     * 用户注册
     * @param username
     * @param password
     * @throws LogicException
     */
    void register(String username, String password) throws LogicException;

    /**
     * 检查用户名
     * @param username
     * @return
     */
    Boolean CheckUsername(String username);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Logininfo login(String username, String password);
}
