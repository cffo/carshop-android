package com.smartbean.carshop.activity;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}