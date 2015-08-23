package com.smartbean.carshop.service;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.alibaba.fastjson.JSONObject;
import com.smartbean.carshop.common.Constants;
import com.smartbean.carshop.entity.UserEntity;
import com.smartbean.carshop.utils.ImageUtils;
import com.ta.TASyncHttpClient;
import com.ta.common.TAStringUtils;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;

import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2015/8/19.
 */
public class UserService {

    private TASyncHttpClient syncHttpClient;
    private AsyncHttpClient asyncHttpClient;

    public UserService(TASyncHttpClient syncHttpClient) {
        this.syncHttpClient = syncHttpClient;
    }

    public UserService(AsyncHttpClient asyncHttpClient) {
        this.asyncHttpClient = asyncHttpClient;
    }

    public UserService(AsyncHttpClient asyncHttpClient, TASyncHttpClient syncHttpClient) {
        this.asyncHttpClient = asyncHttpClient;
        this.syncHttpClient = syncHttpClient;
    }

    public UserEntity login(String userName, String password) {
        RequestParams params = new RequestParams();
        params.put(Constants.PARAM_LOGIN_LOGIN_NAME, userName);
        params.put(Constants.PARAM_LOGIN_PASSWORD, password);
        String result = this.syncHttpClient.get(Constants.INTERFACE_USER_LOGIN, params);
        UserEntity userEntity = new UserEntity();
        if (!TAStringUtils.isBlank(result)) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            boolean success = jsonObject.getBoolean("success");

            if (success) {
                JSONObject resObj = jsonObject.getJSONObject("obj");
                userEntity.setRealName(resObj.getString("realName"));
                userEntity.setUserId(resObj.getString("id"));
                userEntity.setShopId(resObj.getString("shopId"));
                userEntity.setLoginId(resObj.getString("loginId"));
            }
        }
        return userEntity;
    }

    public void getQrCode(String userId, final Handler handler) {
        RequestParams params = new RequestParams();
        params.put(Constants.PARAM_AFTER_SALE_CONSULTANT_ID, userId);
        this.asyncHttpClient.post(Constants.INTERFACE_USER_QRCODE, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                boolean success = jsonObject.getBoolean("success");
                Message msg = new Message();
                Bundle data = new Bundle();
                if (success) {
                    String qrCodeUrl = jsonObject.getString("obj");
                    data.putString("qrCodeUrl", qrCodeUrl);
                    Bitmap bitmap = ImageUtils.getHttpBitmap(qrCodeUrl);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    data.putByteArray("qrcodeBitMap", baos.toByteArray());
                } else {
                    data.putString("qrCodeUrl", null);
                }
                msg.setData(data);
                handler.sendMessage(msg);
            }
        });

    }
}
