package com.scnu.swimmingtrainingsystem.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager{
	
	private boolean noScroll = false;

	public NoScrollViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public void setNoScroll(boolean noScroll){
		this.noScroll = noScroll;
	}
	@Override
	public void scrollTo(int x, int y) {
		// TODO Auto-generated method stub
		super.scrollTo(x, y);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if(noScroll){
			return false;
		}else{
			return super.onTouchEvent(arg0);
		}
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		if(noScroll){
			return false;
		}else{
			return super.onInterceptTouchEvent(arg0);
		}
		
	}
	
	@Override
	public void setCurrentItem(int item) {
		// TODO Auto-generated method stub
		super.setCurrentItem(item);
	}
	
	@Override
	public void setCurrentItem(int item, boolean smoothScroll) {
		// TODO Auto-generated method stub
		super.setCurrentItem(item, smoothScroll);
	}
	

}
