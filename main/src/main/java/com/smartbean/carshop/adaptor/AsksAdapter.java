package com.smartbean.carshop.adaptor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smartbean.carshop.R;

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
public class AsksAdapter extends CommonAdapter {

	private LayoutInflater inflater;

	public AsksAdapter(Activity context, ArrayList<HashMap<String, Object>> data) {
		inflater = LayoutInflater.from(context);
//		this.setCount(10);
		this.activity = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return this.data.size();
	}

	@Override
	public Object getItem(int position) {
		return this.data.get(position);
	}

	@Override
	public View view(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_asks, null);
		}

		HashMap<String, Object> itemData = this.data.get(position);
//		convertView.setTag(position);

		TextView nameTextView = (TextView)convertView.findViewById(R.id.customerName);
		TextView phoneTextView = (TextView)convertView.findViewById(R.id.phone);
		TextView statusTextView = (TextView)convertView.findViewById(R.id.status);
		TextView createTimeTextView = (TextView)convertView.findViewById(R.id.createTime);

		nameTextView.setText("姓名："+(String)itemData.get("customerName"));
		phoneTextView.setText("电话："+(String)itemData.get("phone"));
		statusTextView.setText("处理状态:"+(String)itemData.get("status"));
		createTimeTextView.setText("创建时间："+(String)itemData.get("createTime"));
		return convertView;
	}

}