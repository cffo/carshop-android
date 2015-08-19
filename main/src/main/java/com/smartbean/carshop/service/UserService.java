package com.smartbean.carshop.service;

import android.util.Log;
import com.smartbean.carshop.common.Constants;
import com.ta.TASyncHttpClient;
import com.ta.annotation.TAInject;
import com.ta.util.http.AsyncHttpResponseHandler;
import com.ta.util.http.RequestParams;

/**
 * Created by Administrator on 2015/8/19.
 */
public class UserService {

    private TASyncHttpClient syncHttpClient;
    public UserService(TASyncHttpClient syncHttpClient){
        this.syncHttpClient = syncHttpClient;
    }


    public boolean login(String userName, String password){
        RequestParams params = new RequestParams();
        params.put(Constants.PARAM_LOGIN_USER_NAME, userName);
        params.put(Constants.PARAM_LOGIN_PASSWORD, password);
        String url = "http://auto.honsintech.com/manage/remote/login?username=cheshishang&password=111111";
        this.syncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                // TODO Auto-generated method stub
                super.onSuccess(content);
            }
        });
        return true;
    }
}
