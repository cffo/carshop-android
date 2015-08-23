package com.smartbean.carshop.activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
//import cn.jpush.android.api.JPushInterface;
import com.smartbean.carshop.R;
import com.smartbean.carshop.activity.base.BaseActivity;
import com.smartbean.carshop.adaptor.AsksAdapter;
import com.smartbean.carshop.view.MyListView;
import com.ta.annotation.TAInjectView;

public class AskActivity extends BaseActivity {

    @TAInjectView(id = R.id.ask_list)
    private MyListView orderListView;

    @TAInjectView(id = R.id.ask_toolbar)
    private Toolbar toolbar;

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        toolbar.setTitle(R.string.ask);
    }

    @Override
    protected void onAfterSetContentView(){
        super.onAfterSetContentView();
        orderListView.setAdapter(new AsksAdapter(this, null));
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