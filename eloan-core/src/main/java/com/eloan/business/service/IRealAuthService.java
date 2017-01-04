package com.eloan.business.service;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.query.PageResult;
import com.eloan.business.domain.Realauth;
import com.eloan.business.query.RealAuthQueryObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 实名认证
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2017-01-03 下午 4:06
 **/
public interface IRealAuthService {

    /**
     * 查找实名认证信息
     * @param id
     * @return
     */
    Realauth getRealAuth(Long id);

    /**
     * 上传图片
     * @param file
     */
    String uploadPic(MultipartFile file) throws LogicException;

    /**
     * 添加实名认证申请
     * @param realauth
     */
    void saveRealAuth(Realauth realauth);

    /**
     * 根据条件分页查询所有的实名认证信息
     * @param qo
     * @return
     */
    PageResult listRealauth(RealAuthQueryObject qo);

    /**
     * 实名认证审核
     * @param id
     * @param remark
     * @param state
     */
    void audit(Long id, String remark, Integer state);
}
