package com.eloan.base.util;

import com.eloan.base.Exception.LogicException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

/**
 * <p>
 * 上传文件工具类
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2017-01-05 上午 10:21
 **/
public class UploadFileUtils {

    private static Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
    /**
     * 上传图片
     *
     * @param picFile      图片数据
     * @param relativePath 图片的相对路径
     * @param absolutePath 图片写入磁盘的绝对路径
     * @return 图片路径
     * @throws LogicException
     */
    public static String uploadPic(MultipartFile picFile, String relativePath, String absolutePath) throws LogicException {
        // 允许的图片格式
        final String[] IMAGE_TYPE = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
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

        // 校验是不是真的是图片
        BufferedImage read = null;
        try {
            read = ImageIO.read(picFile.getInputStream());

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        int width = read.getWidth();
        // 不是图片
        if (width <= 0) {
            throw new LogicException("不是真正的图片");
        }
        /** 开始上传图片 **/
        String originalFilename = picFile.getOriginalFilename(); // 图片的原始名字
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            inputStream = picFile.getInputStream();
            bufferedInputStream = new BufferedInputStream(inputStream);
            String picName = UUID.randomUUID().toString();
            File f = new File(absolutePath+ "/" + picName + extName);
            f.createNewFile();// 创建图片文件
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(f));
            byte[] buffer = new byte[1024];
            int copySize;
            // 开始将数据写到图片里面
            while ((copySize = bufferedInputStream.read(buffer)) > 0) {
                bufferedOutputStream.write(buffer, 0, copySize);
            }
            // 返回图片路径
            return relativePath + picName + extName;
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            throw new LogicException("图片上传失败");
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new LogicException("图片上传失败");
        } finally {
            try {
                if (bufferedInputStream != null)
                    bufferedInputStream.close();
                if (bufferedOutputStream != null)
                    bufferedOutputStream.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }

    }
}
