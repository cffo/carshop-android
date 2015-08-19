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
import com.smartbean.carshop.entity.UserEntity;
import com.smartbean.carshop.utils.ImageUtils;
import com.ta.TASyncHttpClient;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/19.
 */
public class CustomersService {

    private TASyncHttpClient syncHttpClient;
    private AsyncHttpClient asyncHttpClient;

    public CustomersService(TASyncHttpClient syncHttpClient){
        this.syncHttpClient = syncHttpClient;
    }

    public CustomersService(AsyncHttpClient asyncHttpClient){
        this.asyncHttpClient = asyncHttpClient;
    }

    public CustomersService(AsyncHttpClient asyncHttpClient, TASyncHttpClient syncHttpClient){
        this.asyncHttpClient = asyncHttpClient;
        this.syncHttpClient = syncHttpClient;
    }

    public void getCustomers(String userId, final Handler handler){
        RequestParams params = new RequestParams();
        params.put(Constants.PARAM_AFTER_SALE_CONSULTANT_ID, userId);
        this.asyncHttpClient.post(Constants.INTERFACE_CUSTOMER_CUSTOMERS, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                boolean success = jsonObject.getBoolean("success");
                Message msg = new Message();
                Bundle data = new Bundle();
                if (success) {
                    JSONArray customers = jsonObject.getJSONArray("obj");
                    List<CustomerEntity> customerEntitys = new ArrayList<CustomerEntity>();
                    CustomerEntityList customerEntityList = new CustomerEntityList();
                    for(int i = 0; i<customers.size(); i++){
                        JSONObject obj = customers.getJSONObject(i);
                        CustomerEntity customerEntity  = new CustomerEntity();
                        customerEntity.setAvatar(obj.getString("avatar"));
                        Bitmap bitmap = ImageUtils.getHttpBitmap(obj.getString("avatar"));
                        customerEntity.setAvatarBitmap(bitmap);
                        customerEntity.setCustomerId(obj.getString("customerId"));
                        customerEntity.setMobile(obj.getString("mobile"));
                        customerEntity.setName(obj.getString("name"));
                        customerEntitys.add(customerEntity);
                    }
                    customerEntityList.customerEntitys = customerEntitys;
                    data.putSerializable("list",customerEntityList);
                } else {
                    data.putString("list", null);
                }

                msg.setData(data);
                handler.sendMessage(msg);
            }
        });
    }

}
