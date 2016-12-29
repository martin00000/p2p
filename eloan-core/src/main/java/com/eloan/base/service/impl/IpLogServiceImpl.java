package com.eloan.base.service.impl;

import com.eloan.base.domain.IpLog;
import com.eloan.base.mapper.IpLogMapper;
import com.eloan.base.query.IpLogQueryObject;
import com.eloan.base.query.PageResult;
import com.eloan.base.service.IpLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 下午 12:31
 **/
@Service
public class IpLogServiceImpl implements IpLogService {

    @Autowired
    private IpLogMapper ipLogMapper;

    @Override
    public PageResult getIpLog(IpLogQueryObject qo) {
        // 分页
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<IpLog> list = ipLogMapper.queryByCondition(qo);
        PageInfo pageInfo = new PageInfo(list);
        long total = pageInfo.getTotal();
        PageResult pageResult = new PageResult((int)total,qo.getPageSize(), qo.getCurrentPage(), list);
        return pageResult;
    }
}
