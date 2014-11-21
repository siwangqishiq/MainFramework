package com.xinlan.mainframe.network;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import android.content.Context;
import lib.asynchronous.AsyncHttpClient;
import lib.asynchronous.AsyncHttpResponseHandler;
import lib.asynchronous.PersistentCookieStore;
import lib.asynchronous.RequestParams;

/**
 * 网络请求控件
 * 
 * @author panyi
 * 
 */
public class NetClient {
	/**
	 * http://loopj.com/android-async-http/doc/index-all.html
	 */
	private static AsyncHttpClient client = new AsyncHttpClient();
	static{
		client.setUserAgent("hdsadsadjj");
	}

	/**
	 * 发送不带cookie的get请求
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(url, params, responseHandler);
	}

	/**
	 * 发送不带cookie的post请求
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(url, params, responseHandler);
	}
	
	public void post(Context context,String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
		client.setCookieStore(myCookieStore);
		for(Cookie cookie :myCookieStore.getCookies()){
			System.out.println(cookie.getName()+"="+cookie.getValue()+"   "+cookie.getDomain());
		}
		params.add("v1", "你好 世界");
		client.post(url, params, responseHandler);
	}
	
}// end class
