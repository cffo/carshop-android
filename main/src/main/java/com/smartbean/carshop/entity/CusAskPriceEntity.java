package com.smartbean.carshop.entity;

import com.smartbean.carshop.common.Constants;
import org.joda.time.DateTime;
import java.util.Date;

/**
 * Created by wangjinwei on 2015/8/23.
 */
public class CusAskPriceEntity {
    private String id;
    private String customerName;
    private String phone;
    private String sysUserName;
    private DateTime createTime;
    private String createTimeStr;
    private String status;
    private String statusStr;

    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTimeStr() {
        return createTime.toString("yyyy-MM-dd HH:mm:ss");
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getStatusStr() {
        if(status != null) {
            if (status.equals(Constants.PREHANDLE)) {
                return "待处理";
            } else if (status.equals(Constants.AGREE)) {
                return "已处理";
            } else if (status.equals(Constants.FINISH)) {
                return "完成";
            }
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}
