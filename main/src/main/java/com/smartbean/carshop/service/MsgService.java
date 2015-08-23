package com.smartbean.carshop.service;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartbean.carshop.common.Constants;
import com.smartbean.carshop.entity.CustomerEntity;
import com.smartbean.carshop.entity.CustomerEntityList;
import com.smartbean.carshop.entity.MessageEntity;
import com.smartbean.carshop.entity.MessageEntityList;
import com.smartbean.carshop.utils.ImageUtils;
import com.ta.TASyncHttpClient;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/19.
 */
public class MsgService {

    private TASyncHttpClient syncHttpClient;
    private AsyncHttpClient asyncHttpClient;

    public MsgService(TASyncHttpClient syncHttpClient){
        this.syncHttpClient = syncHttpClient;
    }

    public MsgService(AsyncHttpClient asyncHttpClient){
        this.asyncHttpClient = asyncHttpClient;
    }

    public MsgService(AsyncHttpClient asyncHttpClient, TASyncHttpClient syncHttpClient){
        this.asyncHttpClient = asyncHttpClient;
        this.syncHttpClient = syncHttpClient;
    }

    public void getMsg(String userId, String customerId, final Handler handler){
        RequestParams params = new RequestParams();
        params.put(Constants.PARAM_USER_ID, userId);
        params.put(Constants.PARAM_CUSTOMER_ID, customerId);

        this.asyncHttpClient.post(Constants.INTERFACE_CUSTOMER_MSG_MESSAGES, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                Message msg = new Message();
                Bundle data = new Bundle();
                JSONArray messages = jsonObject.getJSONArray("obj");
                List<MessageEntity> messageEntities = new ArrayList<MessageEntity>();
                MessageEntityList messageEntityList = new MessageEntityList();
                for(int i = 0; i<messages.size(); i++){
                    JSONObject obj = messages.getJSONObject(i);
                    MessageEntity messageEntity  = new MessageEntity();
                    messageEntity.setUserName(obj.getString("userName"));
                    Bitmap bitmap = ImageUtils.getHttpBitmap(Constants.SERVER_URL+obj.getString("userImg"));
                    messageEntity.setUserImgBitmap(bitmap);
                    messageEntity.setUserId(obj.getString("userId"));
                    messageEntity.setCustomerId(obj.getString("customerId"));
                    messageEntity.setCustomerName(obj.getString("customerName"));
                    Bitmap customerImgBitmap = ImageUtils.getHttpBitmap(obj.getString("customerImg"));
                    messageEntity.setCustomerImgBitmap(customerImgBitmap);
                    messageEntity.setSource(obj.getString("source"));
                    messageEntity.setContent(obj.getString("content"));
                    messageEntities.add(messageEntity);
                }
                messageEntityList.messageEntities = messageEntities;
                data.putSerializable("list",messageEntityList);

                msg.setData(data);
                handler.sendMessage(msg);
            }
        });
    }

    public void sendMsg(String userId, String customerId,String content, final Handler handler){
        RequestParams params = new RequestParams();
        params.put(Constants.PARAM_AFTER_SALE_CONSULTANT_ID, userId);
        params.put(Constants.PARAM_CUSTOMER_ID, customerId);
        params.put(Constants.PARAM_MSG_CONTENT, content);
        this.asyncHttpClient.post(Constants.INTERFACE_CUSTOMER_MSG_SEND, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                boolean success = jsonObject.getBoolean("success");
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putBoolean("success", success);
                msg.setData(data);
                handler.sendMessage(msg);
            }
        });
    }



}
