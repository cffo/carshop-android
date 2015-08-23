package com.smartbean.carshop.activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
//import cn.jpush.android.api.JPushInterface;
import com.smartbean.carshop.R;
import com.smartbean.carshop.activity.base.BaseActivity;
import com.ta.annotation.TAInjectView;


public class MainActivity extends BaseActivity{

    @TAInjectView(id = R.id.main_orders)
    private TextView mainOrdersViews;

    @TAInjectView(id = R.id.main_ask)
    private TextView mainAskViews;

    @TAInjectView(id = R.id.main_customers)
    private TextView mainCustomerViews;

    @TAInjectView(id = R.id.main_qrcode)
    private TextView mainQrcodeViews;

    @TAInjectView(id = R.id.main_toolbar)
    private Toolbar toolbar;

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar.setTitle(R.string.app_name);
    }

    @Override
    protected void onAfterSetContentView(){
        super.onAfterSetContentView();
        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.main_orders:
                        doActivity(R.string.orderActivity);
                        break;
                    case R.id.main_ask:
                        doActivity(R.string.asksActivity);
                        break;
                    case R.id.main_customers:
                        doActivity(R.string.customerActivity);
                        break;
                    case R.id.main_qrcode:
                        doActivity(R.string.qrcodeActivity);
                        break;
                }
            }
        };
        mainOrdersViews.setOnClickListener(onClickListener);
        mainAskViews.setOnClickListener(onClickListener);
        mainCustomerViews.setOnClickListener(onClickListener);
        mainQrcodeViews.setOnClickListener(onClickListener);
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