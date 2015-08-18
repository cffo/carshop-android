package com.smartbean.carshop.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.rey.material.widget.Button;

public class LoginActivity extends BaseActivity {

    private Button loginButton;

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                break;
        }
    }

}