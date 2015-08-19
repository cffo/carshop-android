package com.smartbean.carshop.service;

import com.alibaba.fastjson.JSONObject;
import com.smartbean.carshop.common.Constants;
import com.smartbean.carshop.entity.UserEntity;
import com.ta.TASyncHttpClient;
import com.ta.util.http.RequestParams;

/**
 * Created by Administrator on 2015/8/19.
 */
public class UserService {

    private TASyncHttpClient syncHttpClient;
    public UserService(TASyncHttpClient syncHttpClient){
        this.syncHttpClient = syncHttpClient;
    }


    public UserEntity login(String userName, String password){
        RequestParams params = new RequestParams();
        params.put(Constants.PARAM_LOGIN_LOGIN_NAME, userName);
        params.put(Constants.PARAM_LOGIN_PASSWORD, password);
        String result = this.syncHttpClient.get(Constants.INTERFACE_USER_LOGIN, params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        boolean success = jsonObject.getBoolean("success");
        UserEntity userEntity = new UserEntity();
        if(success){
            JSONObject resObj = jsonObject.getJSONObject("obj");
            userEntity.setRealName(resObj.getString("realName"));
            userEntity.setUserId(resObj.getString("id"));
            userEntity.setShopId(resObj.getString("shopId"));
            userEntity.setLoginId(resObj.getString("loginId"));
        }
        return userEntity;
    }
}
