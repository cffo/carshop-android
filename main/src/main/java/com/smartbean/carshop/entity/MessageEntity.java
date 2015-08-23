package com.smartbean.carshop.entity;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2015/8/20.
 */
public class MessageEntity {
    private String content;
    private String userId;
    private String userName;
    private String userImg;
    private Bitmap userImgBitmap;
    private String customerId;
    private String customerName;
    private String customerImg;
    private Bitmap customerImgBitmap;
    private String source;
    private String sendName;
    private String acceptName;
    private String chatTimeStr;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getAcceptName() {
        return acceptName;
    }

    public void setAcceptName(String acceptName) {
        this.acceptName = acceptName;
    }

    public String getChatTimeStr() {
        return chatTimeStr;
    }

    public void setChatTimeStr(String chatTimeStr) {
        this.chatTimeStr = chatTimeStr;
    }

    public Bitmap getUserImgBitmap() {
        return userImgBitmap;
    }

    public void setUserImgBitmap(Bitmap userImgBitmap) {
        this.userImgBitmap = userImgBitmap;
    }

    public Bitmap getCustomerImgBitmap() {
        return customerImgBitmap;
    }

    public void setCustomerImgBitmap(Bitmap customerImgBitmap) {
        this.customerImgBitmap = customerImgBitmap;
    }
}
