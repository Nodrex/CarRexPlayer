package com.nodrex.carrexplayer.custom;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;

public class CustomRecyclerLayout extends LinearLayoutManager {
	
	public CustomRecyclerLayout(Context context, int orientation, boolean reverseLayout) {
		super(context, orientation, reverseLayout);
	}

	public CustomRecyclerLayout(Context context) {
		super(context);
	}

	public void onLayoutChildren(Recycler arg0, State arg1) {
		try {
			super.onLayoutChildren(arg0, arg1);
		} catch (Exception e) {}
	}
	
	public int scrollVerticallyBy(int dy, Recycler recycler, State state) {
		try {
			return super.scrollVerticallyBy(dy, recycler, state);
		} catch (Exception e) {
			return 0;
		}
	}
	
}