package com.scnu.swimmingtrainingsystem.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.scnu.swimmingtrainingsystem.R;
import com.scnu.swimmingtrainingsystem.util.CommonUtils;
import com.scnu.swimmingtrainingsystem.util.Constants;
import com.scnu.swimmingtrainingsystem.util.NetworkUtil;
import com.scnu.swimmingtrainingsystem.util.VolleyUtil;
import com.scnu.swimmingtrainingsystem.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RetrievePasswordActivity extends Activity {
	private MyApplication app;
//	private RequestQueue mQueue;
	private EditText emailEditText;
	private Toast mToast;
	private LoadingDialog loadingDialog;

	private boolean isConnected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrieve_password);
		app = (MyApplication) getApplication();
		app.addActivity(this);
//		mQueue = Volley.newRequestQueue(this);
		emailEditText = (EditText) findViewById(R.id.edt_email);
	}

	public void back(View v) {
		finish();
		overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
	}

	public void sendEmail(View v) {
		String emailAdress = emailEditText.getText().toString().trim();
		isConnected = NetworkUtil.isConnected(RetrievePasswordActivity.this);
		if(isConnected){
			if (loadingDialog == null) {
				loadingDialog = LoadingDialog.createDialog(this);
				loadingDialog.setMessage(getString(R.string.sending_request));
				loadingDialog.setCanceledOnTouchOutside(false);
			}
			loadingDialog.show();
			sendEmailRequest(emailAdress);
		}else{
			CommonUtils.showToast(this,mToast,getString(R.string.network_error));
		}

	}

	/**
	 * 发送找回密码请求
	 * 
	 * @param s1
	 *            邮箱地址
	 */
	public void sendEmailRequest(final String s1) {

		Map<String,String> map = getDataMap(s1);
		VolleyUtil.ResponseListener listener = new VolleyUtil.ResponseListener() {
			@Override
			public void onSuccess(String string) {
				loadingDialog.dismiss();
				try {
					JSONObject obj = new JSONObject(string);
					int resCode = (Integer) obj.get("resCode");
					if (resCode == 1) {
						CommonUtils.showToast(
								RetrievePasswordActivity.this, mToast,
								getString(R.string.send_succ_check_email));
					} else if (resCode == 0) {
						CommonUtils.showToast(
								RetrievePasswordActivity.this, mToast,
								getString(R.string.user_not_exist));
					} else {
						CommonUtils.showToast(
								RetrievePasswordActivity.this, mToast,
								getString(R.string.server_error));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Handler handler = new Handler();
				Runnable updateThread = new Runnable() {
					public void run() {
						finish();
						overridePendingTransition(R.anim.in_from_left,
								R.anim.out_to_right);
					}
				};
				handler.postDelayed(updateThread, 800);
			}

			@Override
			public void onError(String string) {
				loadingDialog.dismiss();
				CommonUtils.showToast(RetrievePasswordActivity.this,
						mToast, getString(R.string.server_or_network_error));
			}
		};

		VolleyUtil.httpJson(Constants.GET_PASSWORD, Request.Method.POST,map,listener,app);
//
//		StringRequest sendEmailRequest = new StringRequest(Method.POST,
//				Constants.GET_PASSWORD, new Listener<String>() {
//
//					@Override
//					public void onResponse(String response) {
//						// TODO Auto-generated method stub
////						Log.i(Constants.TAG, response);
//						loadingDialog.dismiss();
//						try {
//							JSONObject obj = new JSONObject(response);
//							int resCode = (Integer) obj.get("resCode");
//							if (resCode == 1) {
//								CommonUtils.showToast(
//										RetrievePasswordActivity.this, mToast,
//										getString(R.string.send_succ_check_email));
//							} else if (resCode == 0) {
//								CommonUtils.showToast(
//										RetrievePasswordActivity.this, mToast,
//										getString(R.string.user_not_exist));
//							} else {
//								CommonUtils.showToast(
//										RetrievePasswordActivity.this, mToast,
//										getString(R.string.server_error));
//							}
//
//						} catch (JSONException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//
//						Handler handler = new Handler();
//						Runnable updateThread = new Runnable() {
//							public void run() {
//								finish();
//								overridePendingTransition(R.anim.in_from_left,
//										R.anim.out_to_right);
//							}
//						};
//						handler.postDelayed(updateThread, 800);
//					}
//				}, new ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						// TODO Auto-generated method stub
//						// Log.e(TAG, error.getMessage());
//						loadingDialog.dismiss();
//						CommonUtils.showToast(RetrievePasswordActivity.this,
//								mToast, getString(R.string.cannot_connect_to_server));
//					}
//				}) {
//
//			@Override
//			protected Map<String, String> getParams() throws AuthFailureError {
//				// 设置请求参数
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("email", s1);
//				return map;
//			}
//
//		};
//		sendEmailRequest.setRetryPolicy(new DefaultRetryPolicy(
//				Constants.SOCKET_TIMEOUT,
//				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//		mQueue.add(sendEmailRequest);
	}

	private Map<String,String> getDataMap(String emailString){
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", emailString);
		return map;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
			return false;
		}
		return false;
	}

}
