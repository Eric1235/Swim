package com.scnu.swimmingtrainingsystem.util;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.scnu.swimmingtrainingsystem.activity.MyApplication;

import java.util.Map;

public class VolleyUtil {
	
	/**
	 * 设置请求超时时间（在Application中设置）
	 */
	public static final int TIMEOUT = 8 * 1000;
	
	/**
	 * 监听请求回调接口
	 */
	public static interface ResponseListener {
		
		/**
		 * 请求成功
		 * @param
		 */
		void onSuccess(String string);
		
		/**
		 * 请求失败
		 * @param string 请求失败的信息
		 */
		void onError(String string);
	}
	
	/**
	 * Volley请求方法
	 * @param url 请求地址
	 * @param method 请求的方法
	 * @param params 请求的参数
	 * @param listener 回调接口
	 */
	public static void httpJson(String url, int method, final Map<String, String> params, 
			final ResponseListener listener,MyApplication app) {
		
		StringRequest stringRequest = new StringRequest(method, url, 
		    new Response.Listener<String>() {
			
		        @Override
		        public void onResponse(String response) {
		            listener.onSuccess(response.toString());
		        }
		    }, 
		    new Response.ErrorListener() {
		    	
		        @Override
		        public void onErrorResponse(VolleyError error) {
		        	
		        	if (error.networkResponse != null) {
		        		byte[] htmlBodyBytes = error.networkResponse.data;
		        		listener.onError(new String(htmlBodyBytes));
		        	} else {
		        		listener.onError(null);
		        	}
		        }
		    })
		{       
		    
		    @Override
		    protected Response<String> parseNetworkResponse(NetworkResponse response) {
		    	Log.d("weicong", "Status -> " + response.statusCode);
		    	return super.parseNetworkResponse(response);
		    }
		    
		    @Override
		    protected Map<String, String> getParams() throws AuthFailureError {
		    	return params;
		    }
		    
		};
		
		//将请求添加到队列中
		app.addToRequestQueue(stringRequest);
	}
	


	
}
