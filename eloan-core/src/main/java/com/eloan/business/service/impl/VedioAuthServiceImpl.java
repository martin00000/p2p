package com.eloan.business.service.impl;

import com.eloan.base.domain.Logininfo;
import com.eloan.base.query.PageResult;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.domain.Vedioauth;
import com.eloan.business.mapper.UserinfoMapper;
import com.eloan.business.mapper.VedioauthMapper;
import com.eloan.business.query.VedioAuthQueryObject;
import com.eloan.business.service.IVedioAuthService;
import com.eloan.business.util.BitStatesUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VedioAuthServiceImpl implements IVedioAuthService {

    @Autowired
    private VedioauthMapper vedioauthMapper;

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public PageResult query(VedioAuthQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Vedioauth> list = this.vedioauthMapper.query(qo);
        PageInfo<Vedioauth> info = new PageInfo<>(list);
        long total = info.getTotal();
        return new PageResult((int) total, qo.getPageSize(), qo.getCurrentPage(), list);
    }

    @Override
    public void audit(Long loginInfoValue, String remark, int state) {
        //思路?
        Vedioauth va = new Vedioauth();
        Logininfo applier = new Logininfo();
        applier.setId(loginInfoValue);

        va.setApplier(applier);
        va.setApplyTime(new Date());
        va.setAuditor(UserContext.getLogininfo());
        va.setAuditTime(new Date());
        va.setState(state);
        va.setRemark(remark);
        this.vedioauthMapper.insert(va);

        if (state == Vedioauth.STATE_PASS) {
            Userinfo userinfo = this.userinfoMapper.selectByPrimaryKey(loginInfoValue);
            if (!userinfo.getVedioAuth()) {
                userinfo.addState(BitStatesUtils.OP_VEDIO_AUTH);
                this.userinfoMapper.updateByPrimaryKey(userinfo);
            }
        }
    }
}
