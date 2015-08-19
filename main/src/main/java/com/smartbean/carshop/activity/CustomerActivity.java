package com.smartbean.carshop.activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import com.smartbean.carshop.activity.base.BaseActivity;
import com.smartbean.carshop.adaptor.CustomerAdapter;
import com.smartbean.carshop.common.Constants;
import com.smartbean.carshop.entity.CustomerEntity;
import com.smartbean.carshop.entity.CustomerEntityList;
import com.smartbean.carshop.service.CustomersService;
import com.smartbean.carshop.view.MyListView;
import com.ta.annotation.TAInject;
import com.ta.annotation.TAInjectView;
import com.ta.common.TAStringUtils;
import com.ta.util.db.entity.TAHashMap;
import com.ta.util.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerActivity extends BaseActivity {

    @TAInjectView(id = R.id.customer_list)
    private MyListView orderListView;

    @TAInjectView(id = R.id.customer_toolbar)
    private Toolbar toolbar;

    @TAInject
    private AsyncHttpClient asyncHttpClient;

    CustomersService customerService;

    SharedPreferences userInfo;


    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        toolbar.setTitle(R.string.customer);
    }

    @Override
    protected void onAfterOnCreate(Bundle savedInstanceState){
        super.onAfterOnCreate(savedInstanceState);

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
            ArrayList<HashMap<String, Object>> customerDatas = new ArrayList<HashMap<String, Object>>();
            for(int i = 0; i < customers.size(); i++){
                HashMap<String, Object> customerData = new HashMap<>();
                customerData.put("avatarBitmap", customers.get(i).getAvatarBitmap());
                customerData.put("mobile", customers.get(i).getMobile());
                customerData.put("name", customers.get(i).getName());
                customerData.put("customerId", customers.get(i).getCustomerId());
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

}