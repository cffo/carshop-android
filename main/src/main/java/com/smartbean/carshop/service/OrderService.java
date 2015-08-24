package com.smartbean.carshop.service;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartbean.carshop.R;
import com.smartbean.carshop.entity.OrderEntity;
import com.ta.TASyncHttpClient;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by wangjinwei on 2015/8/20.
 */
public class OrderService {
    private AsyncHttpClient asyncHttpClient;
    private Context context;
    public OrderService(Context context,AsyncHttpClient asyncHttpClient){
        this.context = context;
        this.asyncHttpClient = asyncHttpClient;
    }

    public void getPreOrders(String userId,final Handler handler){
        RequestParams params = new RequestParams();
        params.put(this.context.getString(R.string.AFTERSALESCONSULTANTID), userId);
        String INTERFACE_USER_ORDERS = this.context.getString(R.string.SERVER_URL) + this.context.getString(R.string.INTERFACE_USER_ORDERS);

        this.asyncHttpClient.get(INTERFACE_USER_ORDERS,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                String orderEntityStr = "";
                JSONObject jsonObject = JSONObject.parseObject(content);
                boolean success = jsonObject.getBoolean("success");
                if(success){
                    JSONArray jsonArray = jsonObject.getJSONArray("obj");
                    if(jsonArray!=null&&!jsonArray.isEmpty()){
                        orderEntityStr = jsonArray.toJSONString();
                    }

                }
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("preOrders",orderEntityStr);

                msg.setData(data);

                handler.sendMessage(msg);
            }
        });

    }

}
