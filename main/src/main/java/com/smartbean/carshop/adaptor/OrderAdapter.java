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
 * Description: 绫绘弿锟�?
 *
 * History:
 */
public class OrderAdapter extends CommonAdapter {

	private LayoutInflater inflater;

	public OrderAdapter(Activity context, ArrayList<HashMap<String, Object>> data) {
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
			convertView = inflater.inflate(R.layout.item_order, null);
		}
		HashMap<String, Object> itemData = this.data.get(position);
		convertView.setTag(position);
		TextView nameTextView = (TextView)convertView.findViewById(R.id.customerName);
		TextView phoneTextView = (TextView)convertView.findViewById(R.id.phone);
		TextView startTimeTextView = (TextView)convertView.findViewById(R.id.startTime);
		TextView endTimeTextView = (TextView)convertView.findViewById(R.id.endTime);
		TextView statusTextView = (TextView)convertView.findViewById(R.id.status);
		nameTextView.setText("姓名："+(String)itemData.get("customerName"));
		phoneTextView.setText("电话："+(String)itemData.get("phone"));
		startTimeTextView.setText("预约开始时间："+(String)itemData.get("startTime"));
		endTimeTextView.setText("预约截止时间："+(String)itemData.get("endTime"));
		statusTextView.setText("预约状态："+(String)itemData.get("status"));
		return convertView;
	}

}