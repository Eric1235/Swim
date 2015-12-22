package com.scnu.swimmingtrainingsystem.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.scnu.swimmingtrainingsystem.R;
import com.scnu.swimmingtrainingsystem.util.AppController;
import com.scnu.swimmingtrainingsystem.util.SpUtil;

/**
 * 欢迎导航Activity
 * @author LittleByte
 *
 */
public class WlecomeActivity extends Activity {
	private MyApplication app;
	private AlphaAnimation start_anima;
	private View view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		view = View.inflate(this, R.layout.activity_wlecome, null);
		setContentView(view);
		app = (MyApplication) getApplication();
		app.addActivity(this);
		initView();
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
	}

	private void initView() {
		// TODO Auto-generated method stub
		start_anima = new AlphaAnimation(0.3f, 1.0f);
		start_anima.setDuration(2000);
		view.startAnimation(start_anima);
		start_anima.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				redirectTo();
			}
		});
	}

	/**
	 * 跳转至登录页面
	 */
	private void redirectTo() {
		boolean isLogin = SpUtil.getIsLogin(WlecomeActivity.this);
		if(isLogin){
			AppController.gotoHomeActivity(WlecomeActivity.this);
		}else{
			AppController.gotoLogin(WlecomeActivity.this);
			finish();
		}
	}
}
