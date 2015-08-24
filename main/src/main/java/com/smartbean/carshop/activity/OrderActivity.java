package com.smartbean.carshop.activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rey.material.widget.TextView;
import com.smartbean.carshop.R;
import com.smartbean.carshop.activity.base.BaseActivity;
import com.smartbean.carshop.adaptor.OrderAdapter;
import com.smartbean.carshop.common.Constants;
import com.smartbean.carshop.service.OrderService;
import com.smartbean.carshop.view.MyListView;
import com.ta.annotation.TAInject;
import com.ta.annotation.TAInjectView;
import com.ta.common.TAStringUtils;
import com.ta.util.http.AsyncHttpClient;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class OrderActivity extends BaseActivity {

    @TAInjectView(id = R.id.orders_list)
    private MyListView orderListView;

    @TAInjectView(id = R.id.order_toolbar)
    private Toolbar toolbar;

    private SharedPreferences userInfo;
    private String userId;

    private OrderService orderService;

    private OrderAdapter orderAdapter;

    @TAInject
    private AsyncHttpClient asyncHttpClient;
    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);



        userInfo = getSharedPreferences(this.getString(R.string.ENTITY_USER_INFO),0);
        if(userInfo!=null){
            userId = userInfo.getString(this.getString(R.string.PARAM_LOGIN_USERID), "");
        }

        setContentView(R.layout.activity_order);
        toolbar.setTitle(R.string.order);
    }

    @Override
    protected void onAfterOnCreate(Bundle savedInstanceState){
        super.onAfterOnCreate(savedInstanceState);
        orderService = new OrderService(this,asyncHttpClient);
        new Thread(preOrderThread).start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String orderEntityStr = data.getString("preOrders");
            if(TAStringUtils.isBlank(orderEntityStr)){
                //TODO 返回空预约单的逻辑
               /* ViewStub emptyView = (ViewStub)OrderActivity.this.findViewById(R.id.empty_view);
                emptyView.inflate();
                emptyView.setVisibility(View.VISIBLE );
                TextView emptyTextView = (TextView)OrderActivity.this.findViewById(R.id.view_empty_text);
                emptyTextView.setText(OrderActivity.this.getString(R.string.order)+"为空！");*/
            }else{
                JSONArray jsonArray = JSONObject.parseArray(orderEntityStr);
                ArrayList<HashMap<String, Object>> orderEntityList = new ArrayList<HashMap<String, Object>>();
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
//                            hashMap.put("afterSalesConsultantId",resObj.getString("afterSalesConsultantId"));
//                            hashMap.put("startTime",new DateTime(resObj.getString("startTime")));
                            hashMap.put("startTime",resObj.getString("startTimeStr"));
//                            hashMap.put("endTime",new DateTime(resObj.getString("endTime")));
                            hashMap.put("endTime",resObj.getString("endTimeStr"));
//                            hashMap.put("createTime",new DateTime(resObj.getString("createTime")));
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
//                            hashMap.put("statu",resObj.getString("statu"));

                            /*hashMap.put("afterSalesName",resObj.getString("afterSalesName"));
                            hashMap.put("startTimeDate",resObj.getDate("startTimeDate"));
                            hashMap.put("endTimeDate",resObj.getDate("endTimeDate"));
                            hashMap.put("shopId",resObj.getString("shopId"));
                            hashMap.put("arriveTime",new DateTime(resObj.getString("arriveTime")));
                            hashMap.put("carSeriseName",resObj.getString("carSeriseName"));*/
                            orderEntityList.add(hashMap);
                        }
                    }
                }
                orderAdapter = new OrderAdapter(OrderActivity.this,orderEntityList);
                orderListView.setAdapter(orderAdapter);
            }
        }
    };

    Runnable preOrderThread =  new Runnable(){
        @Override
        public void run() {
            orderService.getPreOrders(userId,handler);
        }
    };

    @Override
    protected void onAfterSetContentView(){
        super.onAfterSetContentView();
    }

}