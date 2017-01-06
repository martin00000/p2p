package com.eloan.business.service.impl;

import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.util.UploadFileUtils;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Realauth;
import com.eloan.business.domain.Userfile;
import com.eloan.business.mapper.UserfileMapper;
import com.eloan.business.service.IUserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author Dingying
 * @create 2017-01-04 23:43
 **/
@Service
public class IUserFileServiceImpl implements IUserFileService {

    // 允许的图片格式
    private static final String[] IMAGE_TYPE = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};

    @Autowired
    private UserfileMapper userfileMapper;

    @Override
    public List<Userfile> getUserFileOfCurrentUser(Long applierId, boolean noType) {
        return userfileMapper.selectByApplierId(applierId, noType);
    }

    @Override
    public void uploadFile(MultipartFile picFile) {
        String path = UploadFileUtils.uploadPic(picFile, "/upload/"
                , "E:/ideaworkspace/p2p/eloan-uiweb/src/main/webapp/upload/");
        Userfile userfile = new Userfile();
        userfile.setFile(path);
        userfile.setState(Realauth.STATE_APPLY);
        userfile.setApplier(UserContext.getLogininfo());
        userfile.setApplyTime(new Date());
        userfileMapper.insert(userfile);
    }

    @Override
    public void updateFileTypes(Long[] id, Long[] fileTypeId) {
        for(int i=0;i<id.length;i++){
            Long ufid=id[i];
            Userfile uf=this.userfileMapper.selectByPrimaryKey(ufid);
            if(uf!=null){
                SystemDictionaryItem item=new SystemDictionaryItem();
                item.setId(fileTypeId[i]);
                uf.setFileType(item);
                this.userfileMapper.updateByPrimaryKey(uf);
            }
        }
    }
}
