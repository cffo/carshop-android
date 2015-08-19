package com.smartbean.carshop.activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.smartbean.carshop.activity.base.BaseActivity;
import com.smartbean.carshop.adaptor.CustomerAdapter;
import com.smartbean.carshop.view.MyListView;
import com.ta.annotation.TAInjectView;

public class CustomerActivity extends BaseActivity {

    @TAInjectView(id = R.id.customer_list)
    private MyListView orderListView;

    @TAInjectView(id = R.id.customer_toolbar)
    private Toolbar toolbar;

    @Override
    protected void onPreOnCreate(Bundle savedInstanceState) {
        super.onPreOnCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        toolbar.setTitle(R.string.customer);
    }

    @Override
    protected void onAfterSetContentView(){
        super.onAfterSetContentView();
        orderListView.setAdapter(new CustomerAdapter(this, null));
    }

}