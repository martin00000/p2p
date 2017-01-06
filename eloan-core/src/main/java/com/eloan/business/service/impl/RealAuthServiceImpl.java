package com.eloan.business.service.impl;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.query.PageResult;
import com.eloan.base.util.UploadFileUtils;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Realauth;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.RealauthMapper;
import com.eloan.business.mapper.UserinfoMapper;
import com.eloan.business.query.RealAuthQueryObject;
import com.eloan.business.service.IRealAuthService;
import com.eloan.business.util.BitStatesUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2017-01-03 下午 4:08
 **/
@Service
public class RealAuthServiceImpl implements IRealAuthService {


    @Autowired
    private RealauthMapper realauthMapper;

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public Realauth getRealAuth(Long id) {
        return realauthMapper.selectByPrimaryKey(id);
    }

    @Override
    public String uploadPic(MultipartFile picFile) throws LogicException {
        String picPath = UploadFileUtils.uploadPic(picFile, "/images/"
                , "E:\\ideaworkspace\\p2p\\eloan-uiweb\\src\\main\\webapp\\images");
        return picPath;
    }

    @Override
    @Transactional
    public void saveRealAuth(Realauth realauth) {
        realauth.setState(Realauth.STATE_APPLY);
        realauth.setApplier(UserContext.getLogininfo());
        realauth.setApplyTime(new Date());
        realauthMapper.insert(realauth);
        Userinfo ui = userinfoMapper.selectByPrimaryKey(UserContext.getLogininfo().getId());
        ui.setRealauthId(realauth.getId());
        userinfoMapper.updateByPrimaryKey(ui);
    }

    @Override
    public PageResult listRealauth(RealAuthQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Realauth> realauths = realauthMapper.selectAllByApplierTimeAndState(qo);
        PageInfo<Realauth> info = new PageInfo<>(realauths);
        long total = info.getTotal();
        PageResult result = new PageResult((int) total, qo.getPageSize(), qo.getCurrentPage(), realauths);
        return result;

    }

    @Override
    @Transactional
    public void audit(Long id, String remark, Integer state) {
        Realauth realauth = realauthMapper.selectByPrimaryKey(id);
        realauth.setState(state);
        realauth.setAuditTime(new Date());
        realauth.setAuditor(UserContext.getLogininfo());
        realauth.setRemark(remark);
        realauthMapper.updateByPrimaryKey(realauth);
        if (state == Realauth.STATE_PASS) {
            Userinfo userinfo = userinfoMapper.selectByPrimaryKey(realauth.getApplier().getId());
            userinfo.setRealauthId(id);
            userinfo.setBitState(userinfo.getBitState() + BitStatesUtils.OP_REAL_AUTH);
            userinfo.setRealName(realauth.getRealname());
            userinfo.setIdNumber(realauth.getIdNumber());
            userinfoMapper.updateByPrimaryKey(userinfo);
        }

    }

}
