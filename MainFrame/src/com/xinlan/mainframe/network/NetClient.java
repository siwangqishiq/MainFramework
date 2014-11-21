package com.xinlan.mainframe.network;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import android.content.Context;
import lib.asynchronous.AsyncHttpClient;
import lib.asynchronous.AsyncHttpResponseHandler;
import lib.asynchronous.PersistentCookieStore;
import lib.asynchronous.RequestParams;

/**
 * ��������ؼ�
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
	 * ���Ͳ���cookie��get����
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(url, params, responseHandler);
	}

	/**
	 * ���Ͳ���cookie��post����
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
		params.add("v1", "��� ����");
		client.post(url, params, responseHandler);
	}
	
}// end class
