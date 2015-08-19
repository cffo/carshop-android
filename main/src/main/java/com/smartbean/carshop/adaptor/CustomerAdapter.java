package com.smartbean.carshop.adaptor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smartbean.carshop.activity.R;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Author: pan Email:gdpancheng@gmail.com
 * Created Date:2013-5-8
 * Copyright @ 2013 BU
 * Description: 类描述
 *
 * History:
 */
public class CustomerAdapter extends CommonAdapter {

	private LayoutInflater inflater;

	public CustomerAdapter(Activity context, ArrayList<HashMap<String, Object>> data) {
		inflater = LayoutInflater.from(context);
		this.setCount(10);
		this.activity = context;
		this.data = data;
	}

	@Override
	public View view(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.item_customers, null);
		return convertView;
	}

}