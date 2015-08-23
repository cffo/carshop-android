package com.smartbean.carshop.adaptor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smartbean.carshop.R;
import com.ta.common.TAStringUtils;

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
		this.setCount(data.size());
		this.activity = context;
		this.data = data;
	}

	@Override
	public View view(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.item_customers, null);
		TextView nameTextView = (TextView)convertView.findViewById(R.id.name);
		ImageView headImageView = (ImageView)convertView.findViewById(R.id.head);
		TextView mobilePhoneTextView = (TextView)convertView.findViewById(R.id.mobile_phone);
		String name = (String)data.get(position).get("name");
		String mobilePhone = (String)data.get(position).get("mobile");
		Bitmap avatarBitmap = (Bitmap)data.get(position).get("avatarBitmap");

		if(TAStringUtils.isBlank(mobilePhone)){
			mobilePhone = "暂无电话";
		}
		mobilePhoneTextView.setText("电话："+mobilePhone);
		nameTextView.setText("姓名："+name);
		headImageView.setImageBitmap(avatarBitmap);
		return convertView;
	}

}