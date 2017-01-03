package com.eloan.business.service.impl;

import com.eloan.base.Exception.LogicException;
import com.eloan.base.domain.Logininfo;
import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Mailverify;
import com.eloan.business.domain.MyMailMessage;
import com.eloan.business.domain.Userinfo;
import com.eloan.business.mapper.MailverifyMapper;
import com.eloan.business.mapper.UserinfoMapper;
import com.eloan.business.service.IMailService;
import com.eloan.business.service.ISendVerifyCodeService;
import com.eloan.business.service.IUserInfoService;
import com.eloan.business.util.BitStatesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-29 上午 10:29
 **/
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private ISendVerifyCodeService sendVerifyCodeService;

    @Autowired
    private IMailService mailService;

    @Autowired
    private MailverifyMapper mailverifyMapper;

    @Value("${mail.verifyUrl}")
    private String verifyUrl;

    @Override
    public Userinfo getUserInfo(long id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void bindPhone(String phoneNumber, String verifyCode) throws LogicException {
        if (sendVerifyCodeService.verifyCode(phoneNumber, verifyCode)) {
            Logininfo logininfo = UserContext.getLogininfo();
            Userinfo userinfo = userinfoMapper.selectByPrimaryKey(logininfo.getId());
            // 手机没有绑定
            if (!userinfo.getIsBindPhone()) {
                userinfo.setBitState(BitStatesUtils.OP_BIND_PHONE + userinfo.getBitState());
                userinfo.setPhoneNumber(phoneNumber);
                update(userinfo);
            }

        } else {
            throw new LogicException("验证码错误或者过期!");
        }

    }

    private void update(Userinfo userinfo) throws LogicException {
        int updateCount = userinfoMapper.updateByPrimaryKey(userinfo);
        if (updateCount <= 0) {
            throw new LogicException("系统正忙，请稍后再试!如果多次看到该提示，请联系平台管理员！");
        }
    }

    @Override
    public void sendBindEmail(String email) {
        Logininfo current = UserContext.getLogininfo();
        Userinfo ui = this.userinfoMapper.selectByPrimaryKey(current.getId());
        if (!ui.getIsBindEmail()) {
            MyMailMessage mail = new MyMailMessage();
            Mailverify mv = Mailverify.create(current.getId(), email, 1);
            mail.setTitle("绑定邮箱验证邮件");
            mail.setToAddress(email);
            StringBuilder html = new StringBuilder(100)
                    .append("<html><body><h1>请点击连接：<a href='")
                    .append(verifyUrl).append("?checkCode=")
                    .append(mv.getRandomCode()).append("'>点击这里</a>，完成邮箱绑定，有效期至")
                    .append(DateFormat.getDateInstance().format(mv.getDeadline()))
                    .append(" ！ </h1></body></html>");
            mail.setContent(html.toString());
            mailverifyMapper.insert(mv);
            System.out.println(html.toString());
//            this.mailService.sendMail(mail);
        }
    }

    @Override
    public void bindMailVerify(String checkCode) throws LogicException {
        if (StringUtils.isNotBlank(checkCode)) {
            Mailverify mv = mailverifyMapper.selectByCheckCode(checkCode);
            if (mv != null) {
                Date now = new Date();
                if (now.before(mv.getDeadline())) {
                    Userinfo userinfo = userinfoMapper.selectByPrimaryKey(mv.getUserinfoId());
                    if (!userinfo.getIsBindEmail()) {
                        userinfo.setBitState(BitStatesUtils.OP_BIND_EMAIL + userinfo.getBitState());
                        userinfo.setEmail(mv.getEmail());
                        userinfoMapper.updateByPrimaryKey(userinfo);
                        return;
                    }
                }
            }
        }
        throw new LogicException("验证码无效！");
    }

    @Override
    public void updateBasicInfo(Userinfo userinfo) {
        Logininfo logininfo = UserContext.getLogininfo();
        Userinfo ui = userinfoMapper.selectByPrimaryKey(logininfo.getId());
        ui.setEducationBackground(userinfo.getEducationBackground());
        ui.setHouseCondition(userinfo.getHouseCondition());
        ui.setIncomeGrade(userinfo.getIncomeGrade());
        ui.setKidCount(userinfo.getKidCount());
        ui.setMarriage(userinfo.getMarriage());
        ui.setBitState(BitStatesUtils.OP_BASE_INFO + ui.getBitState());
        userinfoMapper.updateByPrimaryKey(ui);
    }
}
