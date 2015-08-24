package com.smartbean.carshop.entity;

import com.smartbean.carshop.common.Constants;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by wangjinwei on 2015/8/20.
 */
public class OrderEntity {
    private String id;
    private String customerName;
    private String phone;
    private String afterSalesConsultantId;
    private DateTime startTime;
    private String startTimeStr;
    private DateTime endTime;
    private String endTimeStr;
    private DateTime createTime;
    private String createTimeStr;
    private String status;
    private String statu;
    private String statusStr;
    private String afterSalesName;

    private Date startTimeDate;
    private Date endTimeDate;
    private String shopId;
    private DateTime arriveTime;
    private String arriveTimeStr;
    private String carSeriseName;

    public String getCarSeriseName() {
        return carSeriseName;
    }

    public void setCarSeriseName(String carSeriseName) {
        this.carSeriseName = carSeriseName;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getAfterSalesName() {
        return afterSalesName;
    }

    public void setAfterSalesName(String afterSalesName) {
        this.afterSalesName = afterSalesName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getAfterSalesConsultantId() {
        return afterSalesConsultantId;
    }

    public void setAfterSalesConsultantId(String afterSalesConsultantId) {
        this.afterSalesConsultantId = afterSalesConsultantId;
    }

    public Date getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(Date startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public DateTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(DateTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getEndTimeDate() {
        return endTimeDate;
    }

    public void setEndTimeDate(Date endTimeDate) {
        this.endTimeDate = endTimeDate;
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

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTimeStr() {
        return startTime.toString("yyyy-MM-dd HH:mm:ss");
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getArriveTimeStr() {
        return arriveTime.toString("yyyy-MM-dd HH:mm:ss");
    }

    public void setArriveTimeStr(String arriveTimeStr) {
        this.arriveTimeStr = arriveTimeStr;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeStr() {
        if(endTime!=null) {
            return endTime.toString("yyyy-MM-dd HH:mm:ss");
        }
        return "未添加时间";
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        if(createTime!= null) {
            return createTime.toString("yyyy-MM-dd HH:mm:ss");
        }
        return "未添加时间";
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
