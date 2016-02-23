package com.nodrex.carrexplayer.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class CustomRecyclerView extends RecyclerView{

	public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomRecyclerView(Context context) {
		super(context);
	}

	public void scrollTo(int x, int y) {
		try {
			super.scrollTo(x, y);
		} catch (Exception e) {}
	}
	
	public void setScrollX(int value) {
		try {
			super.setScrollX(value);
		} catch (Exception e) {}
	}
	
	public void setScrollY(int value) {
		try {
			super.setScrollY(value);
		} catch (Exception e) {}
	}
	
}