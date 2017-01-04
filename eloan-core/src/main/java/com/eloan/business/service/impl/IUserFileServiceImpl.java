package com.eloan.business.service.impl;

import com.eloan.base.Exception.LogicException;
import com.eloan.business.domain.Userfile;
import com.eloan.business.mapper.UserfileMapper;
import com.eloan.business.service.IUserFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
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
        // 校验图片不能为空
        if (picFile == null || picFile.isEmpty()) {
            throw new LogicException("图片为空");
        }
        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(picFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }
        if (!isLegal) {
            throw new LogicException("不支持的图片格式");
        }
        try {
            // 校验是不是真的是图片
            InputStream inputStream = picFile.getInputStream();
            BufferedImage read = ImageIO.read(inputStream);
            read.getWidth();

            /*** 开始上传图片到服务器   ***/
            String filePath = RealAuthServiceImpl.writePic(picFile);
            // 获取文件名
//            String originalFilename = picFile.getOriginalFilename();
//            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//            FileOutputStream fos = new FileOutputStream("/images/" + UUID.randomUUID() + extName);
//            BufferedInputStream bis =
//            FastDFSClient fastDFSClient = new FastDFSClient("properties/client.conf");
//            String url = fastDFSClient.uploadFile(picFile.getBytes(), extName);
            // 把url响应给客户
//            url = IMAGE_BASE_URL + url;
//            return PictureResult.ok(url);

        } catch (Exception e) {
            e.printStackTrace();
            throw new LogicException("图片上传失败");
        }
    }
}
