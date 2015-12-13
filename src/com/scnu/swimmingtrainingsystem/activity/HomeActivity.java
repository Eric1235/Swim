package com.scnu.swimmingtrainingsystem.activity;

import java.util.ArrayList;
import java.util.List;









import com.scnu.swimmingtrainingsystem.R;
import com.scnu.swimmingtrainingsystem.fragment.AthleteFragment;
import com.scnu.swimmingtrainingsystem.fragment.MineFragment;
import com.scnu.swimmingtrainingsystem.fragment.OtherFunctionFragment;
import com.scnu.swimmingtrainingsystem.fragment.QueryScoreFragment;
import com.scnu.swimmingtrainingsystem.fragment.TimerSettingFragment;
import com.scnu.swimmingtrainingsystem.view.ChangeColorIconWithText;
import com.scnu.swimmingtrainingsystem.view.NoScrollViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
/**
 * 
 * @author lixinkun
 *
 * 2015年12月10日
 */
public class HomeActivity extends FragmentActivity implements OnClickListener,OnPageChangeListener{
	
	private NoScrollViewPager mViewPager;
	private Fragment mFragment;
	private List<ChangeColorIconWithText>mIndicators = new ArrayList<ChangeColorIconWithText>();

	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		

		initViews();
		
		mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
		mViewPager.setOnPageChangeListener(this);
	}
	
	
	private void initViews() {
		// TODO Auto-generated method stub
		mViewPager = (NoScrollViewPager) findViewById(R.id.viewpager);
		//设置这个viewpager不能够滑动了
		mViewPager.setNoScroll(true);
		ChangeColorIconWithText index = (ChangeColorIconWithText) findViewById(R.id.fragment_index);
		mIndicators.add(index);
		ChangeColorIconWithText consume = (ChangeColorIconWithText) findViewById(R.id.fragment_consume);
		mIndicators.add(consume);
		ChangeColorIconWithText chart = (ChangeColorIconWithText) findViewById(R.id.fragment_chart);
		mIndicators.add(chart);
		ChangeColorIconWithText all = (ChangeColorIconWithText) findViewById(R.id.fragment_all);
		mIndicators.add(all);
		ChangeColorIconWithText more = (ChangeColorIconWithText) findViewById(R.id.fragment_more);
		mIndicators.add(more);
		
		index.setOnClickListener(this);
		consume.setOnClickListener(this);
		chart.setOnClickListener(this);
		all.setOnClickListener(this);
		more.setOnClickListener(this);
		
		index.setIconAlpha(1.0f);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPageScrolled(int position, float positionoffset, int positionoffsetpix) {
		// TODO Auto-generated method stub
		if(positionoffset > 0){
			ChangeColorIconWithText left = mIndicators.get(position);
			ChangeColorIconWithText right = mIndicators.get(position+1);
			left.setIconAlpha(1-positionoffset);
			right.setIconAlpha(positionoffset);
		}
		
	}


	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void clickTab(View v) {
		// TODO Auto-generated method stub
		
		resetTabToNormal();
		switch (v.getId()) {
		case R.id.fragment_index:
			mIndicators.get(0).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(0,false);
			break;
		case R.id.fragment_consume:
			mIndicators.get(1).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(1,false);
			break;

		case R.id.fragment_chart:
			mIndicators.get(2).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(2,false);
			break;

		case R.id.fragment_all:
			mIndicators.get(3).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(3,false);
			break;

		case R.id.fragment_more:
			mIndicators.get(4).setIconAlpha(1.0f);
			mViewPager.setCurrentItem(4,false);
			break;


		default:
			break;
		}
	}

	private void resetTabToNormal() {
		// TODO Auto-generated method stub
		for(int i = 0; i < mIndicators.size();i++){
			mIndicators.get(i).setIconAlpha(0f);
		}
	}
	
	class PagerAdapter extends FragmentStatePagerAdapter{

		public PagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mIndicators.size();
		}
		
		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			switch(position){
			case 0:
				mFragment = new AthleteFragment();
				break;
			case 1:
				mFragment = new TimerSettingFragment();
				break;
			case 2:
				mFragment = new QueryScoreFragment();
				break;
			case 3:
				mFragment = new OtherFunctionFragment();
				break;
			case 4:
				mFragment = new MineFragment();
				break;
			default:
				break;
			}
			return mFragment;
		}
	}
	

}
