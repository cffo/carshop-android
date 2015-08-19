package com.smartbean.carshop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

/*
 * Author: pan Email:gdpancheng@gmail.com
 * Created Date:2013-8-5
 * Copyright @ 2013 BU
 * Description: 类描述
 *
 * History:
 */
public class MyListView extends ListView implements OnScrollListener {
	private GestureDetector mGestureDetector;
	OnTouchListener mGestureListener;

	public MyListView(Context context) {
		super(context);
	}

	@SuppressWarnings("deprecation")
    public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		setFadingEdgeLength(0);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
	}

	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			if (distanceY != 0 && distanceX != 0) {

			}

			if (Math.abs(distanceY) >= Math.abs(distanceX)) {
				return true;
			}
			return false;
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
	}
}
