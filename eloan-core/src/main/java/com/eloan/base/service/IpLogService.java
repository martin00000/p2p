package com.eloan.base.service;

import com.eloan.base.query.IpLogQueryObject;
import com.eloan.base.query.PageResult;

/**
 * <p>
 * 登录日志
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 上午 11:25
 **/
public interface IpLogService {

    /**
     * 范围查询登录日志
     * @param qo
     * @return
     */
    PageResult getIpLog(IpLogQueryObject qo);

}
