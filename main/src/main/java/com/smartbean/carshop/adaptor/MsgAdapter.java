package com.smartbean.carshop.adaptor;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
public class MsgAdapter extends CommonAdapter {

	private LayoutInflater inflater;

	public MsgAdapter(Activity context, ArrayList<HashMap<String, Object>> data) {
		inflater = LayoutInflater.from(context);
		this.setCount(data.size());
		this.activity = context;
		this.data = data;
	}

	@Override
	public View view(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.item_msg, null);

		String source = (String)data.get(position).get("source");
		String content = (String)data.get(position).get("content");
		if("CUSTOMER".equals(source)){
			convertView.findViewById(R.id.user_msg).setVisibility(View.GONE);
			Bitmap customerImgBitmap = (Bitmap)data.get(position).get("customerImgBitmap");
			TextView customerTextView = (TextView)convertView.findViewById(R.id.customer_content);
			ImageView customerHeadImageView = (ImageView)convertView.findViewById(R.id.customer_head);
			customerHeadImageView.setImageBitmap(customerImgBitmap);
			customerTextView.setText(content);
		}else{
			convertView.findViewById(R.id.customer_msg).setVisibility(View.GONE);
			Bitmap userImgBitmap = (Bitmap)data.get(position).get("userImgBitmap");
			TextView userTextView = (TextView)convertView.findViewById(R.id.user_content);
			ImageView userHeadImageView = (ImageView)convertView.findViewById(R.id.user_head);
			userHeadImageView.setImageBitmap(userImgBitmap);
			userTextView.setText(content);
		}
		return convertView;
	}

}