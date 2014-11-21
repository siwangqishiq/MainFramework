package com.xinlan.mainframe;

import org.apache.http.Header;

import com.xinlan.mainframe.network.NetClient;

import lib.asynchronous.DataAsyncHttpResponseHandler;
import lib.asynchronous.RequestParams;
import lib.asynchronous.TextHttpResponseHandler;
import lib.imageloader.core.DisplayImageOptions;
import lib.imageloader.core.ImageLoader;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFrameActivity extends FragmentActivity {
	private ImageView imageView;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.image);
		textView = (TextView) findViewById(R.id.text);
		ImageLoader
				.getInstance()
				.displayImage(
						"http://sale.suning.com/images/advertise/011007/20141114cwsdsn/images/20141117chuwei_01.jpg",
						imageView,
						new DisplayImageOptions.Builder().showImageOnLoading(
								R.drawable.ic_launcher).build());

		NetClient netClient = new NetClient();
		
		RequestParams params=new RequestParams();
		params.add("hello", "world");
		netClient.post(this, "http://10.24.64.78:8080/Demo1/servlet/PhoneRequest", params, new TextHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode,
					Header[] headers, String responseString,
					Throwable throwable) {
				System.out.println("error---->"
						+ responseString);
			}

			@Override
			public void onSuccess(int statusCode,
					Header[] headers, String data) {
				System.out.println("data---->" + data);
			}
		});
	}
}// end class
