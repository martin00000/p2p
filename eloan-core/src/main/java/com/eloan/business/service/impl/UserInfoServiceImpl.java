package com.eloan.business.service.impl;

import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.UserinfoMapper;
import com.eloan.business.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 上午 10:29
 **/
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public Userinfo getUserInfo(long id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }
}
