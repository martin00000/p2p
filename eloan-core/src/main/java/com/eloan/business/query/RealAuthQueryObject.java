package com.eloan.business.query;

import com.eloan.base.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RealAuthQueryObject extends BaseAuditQueryObject {
    private int status = -1;
    private Date beginDate;
    private Date endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setBeginDate(Date date) {
        this.beginDate = date;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEndDate(Date date) {
        this.endDate = date;
    }

    public Date getEndDate() {
        if (endDate != null)
            return DateUtil.endOfDay(endDate);
        return null;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public Date getBeginDate() {
        return beginDate;
    }
}
