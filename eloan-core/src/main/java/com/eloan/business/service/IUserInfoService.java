package com.eloan.business.service;

import com.eloan.business.domain.Userinfo;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 上午 9:57
 **/
public interface IUserInfoService {

    /**
     * 根据id查找用户基本信息
     * @return
     */
    Userinfo getUserInfo(long id);
}
