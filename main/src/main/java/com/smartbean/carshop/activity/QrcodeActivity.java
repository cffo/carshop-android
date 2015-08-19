package com.smartbean.carshop.activity;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import com.smartbean.carshop.activity.base.BaseActivity;
import com.smartbean.carshop.common.Constants;
import com.smartbean.carshop.service.UserService;
import com.smartbean.carshop.utils.ImageUtils;
import com.ta.annotation.TAInject;
import com.ta.annotation.TAInjectView;
import com.ta.common.TAStringUtils;
import com.ta.util.http.AsyncHttpClient;

public class QrcodeActivity extends BaseActivity {

    @TAInjectView(id = R.id.qrcode_toolbar)
    private Toolbar toolbar;

    @TAInjectView(id =  R.id.qrcode)
    private ImageView qrcodeImageView;

    @TAInject
    private AsyncHttpClient asyncHttpClient;

    UserService userService;

    SharedPreferences userInfo;

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        toolbar.setTitle(R.string.qrcode);
    }

    @Override
    protected void onAfterOnCreate(Bundle savedInstanceState) {
        super.onAfterOnCreate(savedInstanceState);
        userInfo = getSharedPreferences(Constants.ENTITY_USER_INFO, 0);
        userService = new UserService(asyncHttpClient);
        new Thread(qrCodeThread).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String qrCodeUrl = data.getString("qrCodeUrl");
            if(!TAStringUtils.isBlank(qrCodeUrl)){
                byte[] b = data.getByteArray("qrcodeBitMap");
                qrcodeImageView.setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length));
            }else{
                qrcodeImageView.setImageResource(R.drawable.logo);
            }
        }
    };

    Runnable qrCodeThread =  new Runnable(){
        @Override
        public void run() {
            userService.getQrCode(userInfo.getString(Constants.PARAM_USER_ID,""), handler);
        }
    };

}