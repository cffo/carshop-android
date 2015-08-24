package com.smartbean.carshop.service;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartbean.carshop.R;
import com.ta.TASyncHttpClient;
import com.ta.util.http.AsyncHttpClient;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;
import android.os.Handler;
/**
 * Created by wangjinwei on 2015/8/23.
 */
public class CustomerAskService {
    private AsyncHttpClient asyncHttpClient;
    private Context context;
    public CustomerAskService(Context context,AsyncHttpClient asyncHttpClient){
        this.context = context;
        this.asyncHttpClient = asyncHttpClient;
    }

    public void getPreCustomerAsks(String userId,final Handler handler){
        RequestParams params = new RequestParams();
        params.put(this.context.getString(R.string.AFTERSALESCONSULTANTID), userId);
        String INTERFACE_ASKS = this.context.getString(R.string.SERVER_URL) + this.context.getString(R.string.INTERFACE_ASKS);
        this.asyncHttpClient.post(INTERFACE_ASKS, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                String customerAsksEntityStr = "";
                JSONObject jsonObject = JSONObject.parseObject(content);
                boolean success = jsonObject.getBoolean("success");
                if (success) {
                    JSONArray jsonArray = jsonObject.getJSONArray("obj");
                    if (jsonArray != null && !jsonArray.isEmpty()) {
                        customerAsksEntityStr = jsonArray.toJSONString();
                    }

                }
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("preCustomerAsks", customerAsksEntityStr);

                msg.setData(data);

                handler.sendMessage(msg);

            }

        });
    }

}
