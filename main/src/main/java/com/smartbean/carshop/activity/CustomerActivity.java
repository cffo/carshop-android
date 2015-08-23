package com.smartbean.carshop.activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
//import cn.jpush.android.api.JPushInterface;
import com.smartbean.carshop.R;
import com.smartbean.carshop.activity.base.BaseActivity;
import com.smartbean.carshop.adaptor.CustomerAdapter;
import com.smartbean.carshop.common.Constants;
import com.smartbean.carshop.entity.CustomerEntity;
import com.smartbean.carshop.entity.CustomerEntityList;
import com.smartbean.carshop.service.CustomersService;
import com.smartbean.carshop.view.MyListView;
import com.ta.annotation.TAInject;
import com.ta.annotation.TAInjectView;

import com.ta.util.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @TAInjectView(id = R.id.customer_list)
    private MyListView orderListView;

    @TAInjectView(id = R.id.customer_toolbar)
    private Toolbar toolbar;

    @TAInject
    private AsyncHttpClient asyncHttpClient;

    CustomersService customerService;

    SharedPreferences userInfo;


    ArrayList<HashMap<String, Object>> customerDatas = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        toolbar.setTitle(R.string.customer);
    }

    @Override
    protected void onAfterOnCreate(Bundle savedInstanceState){
        super.onAfterOnCreate(savedInstanceState);

        orderListView.setOnItemClickListener(this);

        userInfo = getSharedPreferences(Constants.ENTITY_USER_INFO, 0);
        customerService = new CustomersService(asyncHttpClient);
        new Thread(customerThread).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            CustomerEntityList customerEntityList = (CustomerEntityList)data.getSerializable("list");
            List<CustomerEntity> customers = customerEntityList.customerEntitys;

            for(int i = 0; i < customers.size(); i++){
                HashMap<String, Object> customerData = new HashMap<>();
                customerData.put("avatarBitmap", customers.get(i).getAvatarBitmap());
                customerData.put("mobile", customers.get(i).getMobile());
                customerData.put("name", customers.get(i).getName());
                customerData.put("customerId", customers.get(i).getCustomerId());
                customerData.put("customerHeadImg", customers.get(i).getAvatar());
                customerDatas.add(customerData);
            }
            orderListView.setAdapter(new CustomerAdapter(CustomerActivity.this, customerDatas));
        }
    };

    Runnable customerThread =  new Runnable(){
        @Override
        public void run() {
            customerService.getCustomers(userInfo.getString(Constants.PARAM_USER_ID, ""), handler);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle data = new Bundle();
        HashMap<String, Object> userInfo = customerDatas.get(position);
        data.putString("name",userInfo.get("name").toString());
        data.putString("customerId", userInfo.get("customerId").toString());
        data.putString("customerHeadImg", userInfo.get("customerHeadImg").toString());
        doActivity(R.string.sendMsgActivity, data);
    }

//    @Override
//    public void onResume(){
//        JPushInterface.onResume(this);
//    }
//
//    @Override
//    public void onPause(){
//        JPushInterface.onPause(this);
//    }
}