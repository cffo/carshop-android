package com.smartbean.carshop.activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartbean.carshop.R;
import com.smartbean.carshop.activity.base.BaseActivity;
import com.smartbean.carshop.adaptor.AsksAdapter;
import com.smartbean.carshop.common.Constants;
import com.smartbean.carshop.service.CustomerAskService;
import com.smartbean.carshop.view.MyListView;
import com.ta.TASyncHttpClient;
import com.ta.annotation.TAInject;
import com.ta.annotation.TAInjectView;
import com.ta.common.TAStringUtils;
import com.ta.util.http.AsyncHttpClient;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class AskActivity extends BaseActivity {

    @TAInjectView(id = R.id.ask_list)
    private MyListView asksListView;

    @TAInjectView(id = R.id.ask_toolbar)
    private Toolbar toolbar;

    private SharedPreferences userInfo;
    private String userId;

    private CustomerAskService customerAskService;

    private AsksAdapter asksAdapter;

    @TAInject
    private AsyncHttpClient asyncHttpClient;

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);

        userInfo = getSharedPreferences(this.getString(R.string.ENTITY_USER_INFO),0);
        if(userInfo!=null){
            userId = userInfo.getString(this.getString(R.string.PARAM_LOGIN_USERID), "");
        }

        setContentView(R.layout.activity_ask);
        toolbar.setTitle(R.string.ask);
    }

    @Override
    protected void onAfterOnCreate(Bundle savedInstanceState){
        super.onAfterOnCreate(savedInstanceState);
        customerAskService = new CustomerAskService(this,asyncHttpClient);
        new Thread(preCustomerAskThread).start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String customerAsksEntityStr = data.getString("preCustomerAsks");
            if(TAStringUtils.isBlank(customerAsksEntityStr)){
                //TODO 返回我的客户咨询为空的逻辑

            }else{
                JSONArray jsonArray = JSONObject.parseArray(customerAsksEntityStr);
                ArrayList<HashMap<String, Object>> customerAsksEntityList = new ArrayList<HashMap<String, Object>>();
                if(jsonArray!=null&&!jsonArray.isEmpty()){
                    ListIterator<Object> listIt = jsonArray.listIterator();
                    while(listIt.hasNext()){
                        Object itObj = listIt.next();
                        if(itObj != null){
                            JSONObject resObj = (JSONObject)itObj;
                            HashMap<String, Object> hashMap = new HashMap<String, Object>();
                            hashMap.put("id",resObj.getString("id"));
                            hashMap.put("customerName",resObj.getString("customerName"));
                            hashMap.put("phone",resObj.getString("phone"));
                            hashMap.put("createTime",resObj.getString("createTimeStr"));
                            String status = resObj.getString("status");
                            String statusVal = "未知状态";
                            if(status != null) {
                                if (status.equals(Constants.PREHANDLE)) {
                                    statusVal = "待处理";
                                } else if (status.equals(Constants.AGREE)) {
                                    statusVal = "已处理";
                                } else if (status.equals(Constants.FINISH)) {
                                    statusVal = "完成";
                                }
                            }
                            hashMap.put("status",statusVal);
                            customerAsksEntityList.add(hashMap);
                        }
                    }
                }
                asksAdapter = new AsksAdapter(AskActivity.this,customerAsksEntityList);
                asksListView.setAdapter(asksAdapter);
            }
        }
    };

    Runnable preCustomerAskThread =  new Runnable(){
        @Override
        public void run() {
            customerAskService.getPreCustomerAsks(userId, handler);
        }
    };

    @Override
    protected void onAfterSetContentView(){
        super.onAfterSetContentView();
//        orderListView.setAdapter(new AsksAdapter(this, null));
    }
}