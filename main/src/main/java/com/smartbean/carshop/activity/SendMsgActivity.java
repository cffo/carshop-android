package com.smartbean.carshop.activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
//import cn.jpush.android.api.JPushInterface;
import com.rey.material.widget.Button;
import com.rey.material.widget.SnackBar;
import com.smartbean.carshop.R;
import com.smartbean.carshop.activity.base.BaseActivity;
import com.smartbean.carshop.adaptor.AsksAdapter;
import com.smartbean.carshop.adaptor.CustomerAdapter;
import com.smartbean.carshop.adaptor.MsgAdapter;
import com.smartbean.carshop.common.Constants;
import com.smartbean.carshop.entity.CustomerEntity;
import com.smartbean.carshop.entity.CustomerEntityList;
import com.smartbean.carshop.entity.MessageEntity;
import com.smartbean.carshop.entity.MessageEntityList;
import com.smartbean.carshop.service.MsgService;
import com.smartbean.carshop.view.MyListView;
import com.ta.TASyncHttpClient;
import com.ta.annotation.TAInject;
import com.ta.annotation.TAInjectView;
import com.ta.common.TAStringUtils;
import com.ta.mvc.command.TAICommand;
import com.ta.mvc.common.TARequest;
import com.ta.mvc.common.TAResponse;
import com.ta.util.http.AsyncHttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SendMsgActivity extends BaseActivity{

    @TAInjectView(id = R.id.msg_list)
    private MyListView msgListView;

    @TAInjectView(id = R.id.send_sn)
    SnackBar mSnackBar;

    @TAInjectView(id = R.id.send)
    private Button sendButton;
    @TAInjectView(id = R.id.content_text)
    private EditText contentText;

    @TAInjectView(id = R.id.send_msg_toolbar)
    private Toolbar toolbar;

    MsgService msgService;

    @TAInject
    private AsyncHttpClient asyncHttpClient;

    private String userId;
    private String customerId;
    private String name;
    private String headImg;
    private String sendContent;
    SharedPreferences userInfo;
    ArrayList<HashMap<String, Object>> msgsDatas = new ArrayList<HashMap<String, Object>>();

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);

    }

    @Override
    protected void onAfterOnCreate(Bundle savedInstanceState){
        super.onAfterOnCreate(savedInstanceState);
        userInfo = getSharedPreferences(Constants.ENTITY_USER_INFO, 0);
        userId = userInfo.getString(Constants.PARAM_USER_ID, "");
        msgService = new MsgService(asyncHttpClient);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.send:
                        sendContent = contentText.getText().toString().trim();
                        if(TAStringUtils.isBlank(sendContent)){
                            mSnackBar.applyStyle(R.style.SnackBarMultiLine)
                                    .text("发送内容不能为空")
                                    .duration(Constants.DISPLAY_TIME)
                                    .show();
                        }else if((!TAStringUtils.isBlank(sendContent))&&sendContent.length()>100){
                            mSnackBar.applyStyle(R.style.SnackBarMultiLine)
                                    .text("发送内容不能超过100汉字")
                                    .duration(Constants.DISPLAY_TIME)
                                    .show();
                        }else{
                            new Thread(sendMsgThread).start();
                        }
                }
            }
        };
        sendButton.setOnClickListener(onClickListener);


    }

    public void processData(TAResponse response){
        Bundle bundle = (Bundle)response.getData();
        name = bundle.getString("name");
        headImg = bundle.getString("headImg");
        if(TAStringUtils.isBlank(name)){
            toolbar.setTitle(R.string.sendMsg);
        }else{
            toolbar.setTitle(name);
        }
        customerId = bundle.getString("customerId");

        new Thread(getMsgThread).start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            boolean success = data.getBoolean("success");
            if(success){

                HashMap<String, Object> customerData = new HashMap<>();
                customerData.put("userHeadImg", headImg);
                customerData.put("userName", name);
                customerData.put("source", Constants.COUNSELOR);
                customerData.put("content", sendContent);
                msgsDatas.add(customerData);
                msgListView.setAdapter(new MsgAdapter(SendMsgActivity.this, msgsDatas));
                sendContent = "";
                contentText.setText("");
            }else{
                mSnackBar.applyStyle(R.style.SnackBarMultiLine)
                        .text("消息发送失败")
                        .duration(Constants.DISPLAY_TIME)
                        .show();
            }
        }
    };

    Runnable sendMsgThread =  new Runnable(){
        @Override
        public void run() {
            msgService.sendMsg(userInfo.getString(Constants.PARAM_USER_ID, ""), customerId, sendContent, handler);
        }
    };

    Handler messagesHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            MessageEntityList messageEntityList = (MessageEntityList)data.getSerializable("list");
            List<MessageEntity> messageEntities = messageEntityList.messageEntities;

            for(int i = 0; i < messageEntities.size(); i++){
                HashMap<String, Object> customerData = new HashMap<>();
                customerData.put("userImgBitmap", messageEntities.get(i).getUserImgBitmap());
                customerData.put("userName", messageEntities.get(i).getUserName());
                customerData.put("customerImgBitmap", messageEntities.get(i).getCustomerImgBitmap());
                customerData.put("customerName", messageEntities.get(i).getCustomerName());
                customerData.put("source", messageEntities.get(i).getSource());
                customerData.put("content", messageEntities.get(i).getContent());
                msgsDatas.add(customerData);
            }
            msgListView.setAdapter(new MsgAdapter(SendMsgActivity.this, msgsDatas));

        }
    };

    Runnable getMsgThread = new Runnable() {
        @Override
        public void run() {
            msgService.getMsg(userInfo.getString(Constants.PARAM_USER_ID, ""), customerId, messagesHandler);
        }
    };

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