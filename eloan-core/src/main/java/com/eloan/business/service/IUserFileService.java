package com.eloan.business.service;

import com.eloan.business.domain.Userfile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Dingying
 * @create 2017-01-04 23:39
 **/
public interface IUserFileService {
    /**
     * 查询当前用户的疯狂资料信息
     * @param applierId
     * @param noType
     * @return
     */
    List<Userfile> getUserFileOfCurrentUser(Long applierId, boolean noType);

    /**
     * 上传风控资料
     * @param file
     */
    void uploadFile(MultipartFile file);

    /**
     * 更新风控资料的filetype
     * @param id
     * @param fileType
     */
    void updateFileTypes(Long[] id, Long[] fileType);
}
