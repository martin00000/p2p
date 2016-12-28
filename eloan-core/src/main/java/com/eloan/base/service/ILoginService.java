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
    Boolean CheckUsername(String username, Integer userType);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Logininfo login(String username, String password, Integer userType);

    /**
     * 判断是否有管理员账号
     * @return
     */
    boolean hasAdminUser();

    /**
     * 创建默认的管理员账号
     */
    void creatDefaultAdminUser();

}
