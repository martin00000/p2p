package com.eloan.business.service;

import com.eloan.business.domain.MyMailMessage;

/**
 * <p>
 * 邮件
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-30 下午 3:15
 **/
public interface IMailService {
    /**
     * 发送激活邮件
     * @param mail
     */
    void sendMail(MyMailMessage mail);
}
