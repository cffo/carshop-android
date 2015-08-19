package com.smartbean.carshop.activity.base;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import com.ta.TAActivity;
import com.ta.util.netstate.TANetWorkUtil;

/**
 * Created by Administrator on 2015/6/15.
 */
public class BaseActivity extends TAActivity {
    private long firstTime = 0;
    @Override
    protected void onPreOnCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onPreOnCreate(savedInstanceState);
    }

    @Override
    public void onConnect(TANetWorkUtil.netType type)
    {
        // TODO Auto-generated method stub
        super.onConnect(type);
        Toast.makeText(this, "网络连接开启", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDisConnect()
    {
        // TODO Auto-generated method stub
        super.onDisConnect();
        Toast.makeText(this, "网络连接关闭", Toast.LENGTH_LONG).show();
    }
}
