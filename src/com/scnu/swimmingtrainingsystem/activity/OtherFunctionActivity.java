package com.scnu.swimmingtrainingsystem.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.scnu.swimmingtrainingsystem.R;
import com.scnu.swimmingtrainingsystem.fragment.FrequenceFragment;
import com.scnu.swimmingtrainingsystem.fragment.SprintFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他功能界面
 * 这个需要进行大改咯
 * @author LittleByte
 * 
 */
@SuppressLint("SimpleDateFormat")
public class OtherFunctionActivity extends FragmentActivity implements
		OnClickListener {

	private ViewPager viewpager;
	private RadioButton rbThreeTime;
	private RadioButton rbDashTimer;

	private ImageView cursor = null;
	private ImageButton btnBack;
	private Animation animation = null;
	private MyFrageStatePagerAdapter adapter;
	/**
	 * 页面集合
	 */
	List<Fragment> fragmentList = new ArrayList<Fragment>();

	/**
	 * Fragment（页面）
	 */
	SprintFragment sprintFragment;
	FrequenceFragment dashFragment;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other_function);
		init();
//		try {
//			initView();
//			initData();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			startActivity(new Intent(this, LoginActivity.class));
//		}
	}

	private void init() {
		// TODO Auto-generated method stub
		MyApplication app=(MyApplication) getApplication();
		app.addActivity(this);
		viewpager = (ViewPager) findViewById(R.id.vp_fuction);
		rbThreeTime = (RadioButton) findViewById(R.id.rb1);
		rbDashTimer = (RadioButton) findViewById(R.id.rb2);
		btnBack = (ImageButton) findViewById(R.id.btn_back);
		rbDashTimer.setOnClickListener(this);
		rbThreeTime.setOnClickListener(this);
		btnBack.setOnClickListener(this);

		initData();
		select(0);
	}

	private void initData() {
		sprintFragment = new SprintFragment();
		dashFragment = new FrequenceFragment();
		fragmentList.add(dashFragment);
		fragmentList.add(sprintFragment);
		adapter = new MyFrageStatePagerAdapter(getSupportFragmentManager());
		viewpager.setAdapter(adapter);
		viewpager.setOffscreenPageLimit(0);
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				select(viewpager.getCurrentItem());

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rb1:
			viewpager.setCurrentItem(0);
			break;
		case R.id.rb2:
			viewpager.setCurrentItem(1);
			break;
		case R.id.btn_back:
			finish();
		default:
			break;
		}
	}

	/**
	 * 选择对应碎片
	 *
	 * @param index
	 */
	public void select(int index) {
		switch (index) {
		case 0:
			rbThreeTime.setChecked(true);
			break;
		case 1:
			rbDashTimer.setChecked(true);
			break;
		default:
			break;
		}

	}

	class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

		public MyFrageStatePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return fragmentList.get(position);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return POSITION_NONE;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			overridePendingTransition(R.anim.slide_bottom_in,
					R.anim.slide_top_out);
			return false;
		}
		return false;
	}
}
