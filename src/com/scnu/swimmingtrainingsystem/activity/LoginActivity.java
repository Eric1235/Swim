package com.scnu.swimmingtrainingsystem.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.scnu.swimmingtrainingsystem.R;
import com.scnu.swimmingtrainingsystem.db.DBManager;
import com.scnu.swimmingtrainingsystem.effect.Effectstype;
import com.scnu.swimmingtrainingsystem.effect.NiftyDialogBuilder;
import com.scnu.swimmingtrainingsystem.http.JsonTools;
import com.scnu.swimmingtrainingsystem.model.User;
import com.scnu.swimmingtrainingsystem.util.CommonUtils;
import com.scnu.swimmingtrainingsystem.util.Constants;
import com.scnu.swimmingtrainingsystem.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LittleByte
 * 
 */
public class LoginActivity extends Activity {
	/**
	 * 默认用户帐号
	 */
	private static final String DEFAULT_USERNAME = "lixinkun";
	/**
	 * 默认用户的密码
	 */
	private static final String DEFAULT_PASSWORD = "123456";
	private MyApplication app;
	private DBManager dbManager;

	private EditText etLogin;
	private EditText etPassword;
	private TextView sethost;
	private TextView forgot;
	private Toast toast;
	private RequestQueue mQueue;
	private LoadingDialog loadingDialog;
	private Effectstype effect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		intiView();
		initData();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		app = (MyApplication) getApplication();
		app.addActivity(this);
		dbManager = DBManager.getInstance();
		// 检查是否有保存的用户名和密码，如果有就回显
		SharedPreferences sp = getSharedPreferences(Constants.LOGININFO,
				Context.MODE_PRIVATE);
		String username = sp.getString("username", DEFAULT_USERNAME);
		etLogin.setText(username);
		String passwrod = sp.getString("password", DEFAULT_PASSWORD);
		etPassword.setText(passwrod);
		mQueue = Volley.newRequestQueue(this);
		boolean isFirst = sp.getBoolean("isFirst", true);
	
		if (isFirst) {
			User defaulrUser = new User();
			defaulrUser.setId(1L);
			defaulrUser.setUsername(DEFAULT_USERNAME);
			defaulrUser.setPassword(DEFAULT_PASSWORD);
			defaulrUser.save();
			showSettingDialog();
			CommonUtils.SaveLoginInfo(this, false);
		}

		// 从SharedPreferences读取服务器地址信息
		SharedPreferences hostSp = getSharedPreferences(Constants.LOGININFO,
				Context.MODE_PRIVATE);
		CommonUtils.HOSTURL = hostSp
				.getString("hostInfo",
						"http://104.160.34.110:8080/SWIMYUE33/httpPost.action?action_flag=");
		testRequest();
	}

	/**
	 * 初始化视图
	 */
	private void intiView() {
		// TODO Auto-generated method stub

		etLogin = (EditText) findViewById(R.id.tv_user);
		etPassword = (EditText) findViewById(R.id.tv_password);
		sethost = (TextView) findViewById(R.id.setting_host);
		forgot = (TextView) findViewById(R.id.forget_password);
		sethost.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showSettingDialog();
			}
		});
		forgot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotoForgetPwd();
			}
		});
	}

	/**
	 * 跳转注册页面
	 * 
	 * @param v
	 */
	public void onRegister(View v) {
		gotoRegister();
	}

	/**
	 * 登录响应
	 * 
	 * @param v
	 */
	public void onLogin(View v) {
		if (CommonUtils.isFastDoubleClick()) {
			return;
		} else {
			String loginString = etLogin.getText().toString().trim();
			String passwordString = etPassword.getText().toString().trim();
			if (TextUtils.isEmpty(loginString)
					|| TextUtils.isEmpty(passwordString)) {
				CommonUtils.showToast(this, toast, getString(R.string.nameorpwd_cannot_be_empty));
			} else {
				// 保存登录信息
				CommonUtils.SaveLoginInfo(this, loginString, passwordString);
				boolean tryConnect = (Boolean) app.getMap().get(
						Constants.IS_CONNECT_SERVER);
				if (tryConnect) {
					if (loadingDialog == null) {
						loadingDialog = LoadingDialog.createDialog(this);
						loadingDialog.setMessage(getString(R.string.logining));
						loadingDialog.setCanceledOnTouchOutside(false);
					}
					loadingDialog.show();
					// 尝试连接服务器，如果连接成功则直接登录
					loginRequest(loginString, passwordString);
				}
			}
		}

	}

	/**
	 * 提交登录请求
	 * 
	 * @param s1
	 *            用户名
	 * @param s2
	 *            密码
	 */
	public void loginRequest(final String s1, final String s2) {

		StringRequest loginRequest = new StringRequest(Method.POST,
				CommonUtils.HOSTURL + "login", new Listener<String>() {

					@Override
					public void onResponse(String response) {
						// TODO Auto-generated method stub
//						Log.i(Constants.TAG, response);
						loadingDialog.dismiss();
						try {
							JSONObject obj = new JSONObject(response);
							int resCode = (Integer) obj.get("resCode");
//							Log.d("lixinkun", response);
							if (resCode == 1) {
								CommonUtils.showToast(LoginActivity.this,
										toast, getString(R.string.login_success));
								String userJson = obj.get("user").toString();
//								Log.d("lixinkun", "userJson = " + userJson);
								User user = JsonTools.getObject(userJson,
										User.class);
								
								int uid = (Integer) obj.get("uid");
								user.setUid(uid);
//								Log.d("lixinkun", ""
//										+ "user = " + user.toString());
								//判断当前用户是否存在与数据库
							
								if (dbManager.getUserByUid(uid) == null) {
//									Log.d("lixinkun", "user first login");
									user.save();
									app.getMap().put(Constants.CURRENT_USER_ID,
											user.getUid());
//									Log.d("lixinkun","uid = " + user.getUid());
									// 用户第一次登陆
									CommonUtils.saveIsThisUserFirstLogin(
											LoginActivity.this, true);

									// 覆盖前一个用户选择的运动员
									CommonUtils.saveSelectedAthlete(
											LoginActivity.this, "");
								} else {
									// 如果该用户信息已存在本地数据库，则取出当前id作为全局变量
//									Log.d("lixinkun", "user not first login");
									int logineduid = dbManager.getUserByUid(uid).getUid();
									
//									Log.d("lixinkun","logineduid = " + logineduid);
									app.getMap().put(Constants.CURRENT_USER_ID,
											logineduid);
								}
								
								gotoMainActivity();
								
								
							} else if (resCode == 2) {
								CommonUtils.showToast(LoginActivity.this,
										toast, getString(R.string.user_donot_exists));
							} else if (resCode == 3) {
								CommonUtils.showToast(LoginActivity.this,
										toast, getString(R.string.pwd_wrong));
							} else {
								CommonUtils.showToast(LoginActivity.this,
										toast, getString(R.string.server_error));
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						// Log.e(TAG, error.getMessage());
						loadingDialog.dismiss();
						app.getMap().put(Constants.IS_CONNECT_SERVER, false);
						CommonUtils
						.showToast(LoginActivity.this, toast, getString(R.string.network_error));
//						showUserSelectDialog();
					}
				}) {

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// 设置请求参数
				Map<String, String> map = new HashMap<String, String>();
				map.put("userName", s1);
				map.put("password", s2);
				return map;
			}

		};
		loginRequest.setRetryPolicy(new DefaultRetryPolicy(
				Constants.SOCKET_TIMEOUT,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mQueue.add(loginRequest);
	}

	/**
	 * 设置服务器IP地址和端口地址对话框
	 * 
	 *
	 */
	protected void showSettingDialog() {

		final NiftyDialogBuilder settingDialog = NiftyDialogBuilder
				.getInstance(this);
		effect = Effectstype.Slit;
		settingDialog.withTitle(getString(R.string.server_ip_port_setting)).withMessage(null)
				.withIcon(getResources().getDrawable(R.drawable.ic_launcher))
				.isCancelableOnTouchOutside(true).withDuration(500)
				.withEffect(effect).withButton1Text(Constants.CANCLE_STRING)
				.withButton2Text(getString(R.string.finish))
				.setCustomView(R.layout.dialog_setting_host, this);
		SharedPreferences hostSp = getSharedPreferences(Constants.LOGININFO,
				Context.MODE_PRIVATE);
		String ip = hostSp.getString("ip", "104.160.34.110");
		String port = hostSp.getString("port", "8080");
		Window window = settingDialog.getWindow();
		final TextView tv_ip = (TextView) window.findViewById(R.id.tv_ip);
		final TextView tv_port = (TextView) window.findViewById(R.id.tv_port);
		tv_ip.setText(ip);
		tv_port.setText(port);

		settingDialog.setButton1Click(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				settingDialog.dismiss();
			}
		}).setButton2Click(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String hostIp = tv_ip.getText().toString().trim();
				String hostPort = tv_port.getText().toString().trim();
				if (TextUtils.isEmpty(hostIp) || TextUtils.isEmpty(hostPort)) {
					CommonUtils.showToast(LoginActivity.this, toast,
							getString(R.string.ip_and_port_notnull));
				} else {
					// String hostUrl = "http://" + hostIp + ":" + hostPort
					// + "/SWIMYUE33/httpPost.action?action_flag=";
					String hostUrl = "http://104.160.34.110:8080/SWIMYUE33/httpPost.action?action_flag=";
					// 保存服务器ip和端口地址到sp
					CommonUtils.HOSTURL = hostUrl;
					CommonUtils.SaveLoginInfo(LoginActivity.this, hostUrl,
							hostIp, hostPort);
					CommonUtils.showToast(LoginActivity.this, toast, getString(R.string.setting_success));
					settingDialog.dismiss();
				}
			}
		}).show();

	}

	/**
	 * 用户无法连接服务器时弹出该对话框，选择默认帐号或已注册的帐号进行登录使用
	 */
	private void showUserSelectDialog() {
		final NiftyDialogBuilder userDialog = NiftyDialogBuilder
				.getInstance(this);
		effect = Effectstype.SlideBottom;
		userDialog
				.withTitle(getString(R.string.cannot_login))
				.withMessage(
						getString(R.string.continute_use_tip))
				.withIcon(getResources().getDrawable(R.drawable.ic_launcher))
				.isCancelableOnTouchOutside(false).withDuration(500)
				// def
				.withEffect(effect).withButton1Text(getString(R.string.default_acount_login))
				.withButton2Text(getString(R.string.login_again))
				// def gone
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						etLogin.setText("defaultUser");
						etPassword.setText("123456asdjkl");
						// 保存登录信息
						CommonUtils.SaveLoginInfo(LoginActivity.this,
								"defaultUser", "123456asdjkl");
						userDialog.dismiss();
						offlineLogin();
					}
				}).setButton2Click(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						app.getMap().put(Constants.IS_CONNECT_SERVER, true);
						userDialog.dismiss();
					}
				}).show();

	}

	/**
	 * 离线登录
	 */
	private void offlineLogin() {
		// 连接服务器失败，则会使用离线功能登录，可以保存数据但暂时无法上传,只是功能试用
//		CommonUtils.showToast(LoginActivity.this, toast, getString(R.string.login_success_and_jump));
//		// 将当前用户id保存为全局变量
//		User user = dbManager.getUserByName("defaultUser");
//		app.getMap().put(Constants.CURRENT_USER_ID, user.getId());
//		Handler handler = new Handler();
//		Runnable updateThread = new Runnable() {
//			public void run() {
//				Intent intent = new Intent(LoginActivity.this,
//						MainActivity.class);
//				LoginActivity.this.startActivity(intent);
//				overridePendingTransition(R.anim.push_right_in,
//						R.anim.push_left_out);
//			}
//		};
//		handler.postDelayed(updateThread, 500);
	}

	/**
	 * 测试请求
	 * 
	 */
	public void testRequest() {

		StringRequest testRequest = new StringRequest(Method.POST,
				CommonUtils.HOSTURL + "connectionTest", new Listener<String>() {

					@Override
					public void onResponse(String response) {
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
					}
				}) {

		};
		testRequest.setRetryPolicy(new DefaultRetryPolicy(
				Constants.SOCKET_TIMEOUT,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mQueue.add(testRequest);
	}
	
	/**
	 * 跳转到主界面
	 */
	private void gotoMainActivity(){
		CommonUtils
		.showToast(LoginActivity.this, toast, getString(R.string.login_success));

		Handler handler = new Handler();
		Runnable updateThread = new Runnable() {
			public void run() {
				Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
				LoginActivity.this.startActivity(intent);
		
				overridePendingTransition(R.anim.push_right_in,
						R.anim.push_left_out);
			}
		};
		handler.postDelayed(updateThread,800);
	}
	
	/**
	 * 跳转到忘记密码
	 */
	private void gotoForgetPwd(){
		LoginActivity.this.startActivity(new Intent(LoginActivity.this,
				RetrievePasswordActivity.class));
		overridePendingTransition(R.anim.push_right_in,
				R.anim.push_left_out);
	}
	
	/**
	 * 跳转到注册页面
	 */
	private void gotoRegister(){
		Intent i = new Intent(this, RegistAcyivity.class);
		startActivity(i);
		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
	}

}
