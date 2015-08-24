package com.smartbean.carshop.common;

/**
 * Created by Administrator on 2015/8/19.
 */
public class Constants {
    public static int DISPLAY_TIME = 3000;

    public final static String APP_KEY = "c5cf6e8dff0487176627eefa";
    public final static String MASTER_SECRET = "04b267f66e9945048d6b0ca6";

    public static String COUNSELOR = "COUNSELOR";

    public static String SERVER_URL = "http://auto.honsintech.com/manage/";
    static String INTERFACE_URL = "http://auto.honsintech.com/manage/remote/";
    public static String INTERFACE_USER_LOGIN = INTERFACE_URL + "login";
    public static String INTERFACE_USER_QRCODE = INTERFACE_URL + "qrCode";
    public static String INTERFACE_CUSTOMER_CUSTOMERS = INTERFACE_URL + "customers";
    public static String INTERFACE_CUSTOMER_MSG_SEND = INTERFACE_URL + "sendMsg";
    public static String INTERFACE_CUSTOMER_MSG_MESSAGES = INTERFACE_URL + "messages";



    public static String PARAM_LOGIN_LOGIN_NAME = "username";
    public static String PARAM_LOGIN_PASSWORD = "password";

    public static String PARAM_MSG_CONTENT = "content";
    public static String PARAM_CUSTOMER_ID = "customerId";
    public static String PARAM_USER_ID = "userId";
    public static String PARAM_AFTER_SALE_CONSULTANT_ID = "afterSalesConsultantId";

    public static String ENTITY_USER_INFO = "userInfo";

    public static final String AGREE = "AGREE";//已处理
    public static final String FINISH = "FINISH";//完成
    public static final String PREHANDLE = "PREHANDLE";//待处理

}
