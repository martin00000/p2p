package com.eloan.business.service.impl;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Realauth;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.RealauthMapper;
import com.eloan.business.service.IRealAuthService;
import com.eloan.business.service.IUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.UUID;

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

    // 允许的图片格式
    private static final String[] IMAGE_TYPE = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};

    @Autowired
    private RealauthMapper realauthMapper;

    @Autowired
    private IUserInfoService userinfoService;

    @Override
    public Realauth getRealAuth(Long id) {
        return realauthMapper.selectByPrimaryKey(id);
    }

    @Override
    public String uploadPic(MultipartFile picFile) throws LogicException {
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
            return writePic(picFile);
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

    @Override
    @Transactional
    public void saveRealAuth(Realauth realauth) {
        realauth.setState(Realauth.STATE_APPLY);
        realauth.setApplier(UserContext.getLogininfo());
        realauth.setApplyTime(new Date());
        realauthMapper.insert(realauth);
        Userinfo ui = userinfoService.getUserInfo(UserContext.getLogininfo().getId());
        ui.setRealauthId(realauth.getId());
    }

    public static String writePic(MultipartFile picFile) throws IOException {
        String originalFilename = picFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            inputStream = picFile.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("/images/" + UUID.randomUUID() + extName));
            byte[] buffer = new byte[1024];
            int copySize;
            while ((copySize = bufferedInputStream.read(buffer)) > 0) {
                bufferedOutputStream.write(buffer, 0, copySize);
            }
            return "/images/" + UUID.randomUUID() + extName;
        } finally {
            try {
                if (bufferedInputStream != null)
                    bufferedInputStream.close();
                if (bufferedOutputStream != null)
                    bufferedOutputStream.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
