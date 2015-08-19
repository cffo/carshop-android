package com.smartbean.carshop.activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.smartbean.carshop.activity.base.BaseActivity;
import com.ta.annotation.TAInjectView;

public class QrcodeActivity extends BaseActivity {

    @TAInjectView(id = R.id.qrcode_toolbar)
    private Toolbar toolbar;


    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        toolbar.setTitle(R.string.qrcode);
    }

}