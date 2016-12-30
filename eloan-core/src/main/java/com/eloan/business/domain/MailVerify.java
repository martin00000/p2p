package com.eloan.business.domain;

import com.eloan.base.domain.BaseDomain;
import com.eloan.base.util.DateUtil;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 邮件验证信息
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2016-12-30 下午 3:48
 **/
public class Mailverify extends BaseDomain {

    private static final long serialVersionUID = -7643082287493857956L;
    private Long userinfoId;
    private Date deadline;
    private String randomCode;
    private String email;

    public static Mailverify create(Long userinfoId,String email, int validateDay) {
        Mailverify mv = new Mailverify();
        mv.setRandomCode(UUID.randomUUID().toString());
        mv.setUserinfoId(userinfoId);
        mv.setEmail(email);
        mv.setDeadline(DateUtil.addDay(new Date(), validateDay));
        return mv;
    }

    public Long getUserinfoId() {
        return userinfoId;
    }

    public void setUserinfoId(Long userinfoId) {
        this.userinfoId = userinfoId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
