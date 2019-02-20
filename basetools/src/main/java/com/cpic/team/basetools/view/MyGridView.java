package com.cpic.team.basetools.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView {

	private Context context;

	public MyGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public MyGridView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}

	public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);

	}
	private void initView(Context context) {
		this.context = context;
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		
		int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, height);
	}
	
	
}
