package com.eloan.business.service.impl;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.query.PageResult;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.List;
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
    private UserinfoMapper userinfoMapper;

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
        PageResult result = new PageResult((int)total, qo.getPageSize(), qo.getCurrentPage(), realauths);
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

    public static String writePic(MultipartFile picFile) throws IOException {
        String originalFilename = picFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            inputStream = picFile.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            String picName = UUID.randomUUID().toString();
            File f = new File("E:/ideaworkspace/p2p/eloan-uiweb/src/main/webapp/images/" + picName + extName);
            f.createNewFile();
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(f));
            byte[] buffer = new byte[1024];
            int copySize;
            while ((copySize = bufferedInputStream.read(buffer)) > 0) {
                bufferedOutputStream.write(buffer, 0, copySize);
            }
            return "/images/" + picName + extName;
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
