package com.xinlan.mainframe;


import org.apache.http.Header;

import lib.asynchronous.AsyncHttpClient;
import lib.asynchronous.AsyncHttpResponseHandler;
import lib.imageloader.core.ImageLoader;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainFrameActivity extends FragmentActivity
{
	private ImageView imageView;
	private TextView textView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.image);
        textView = (TextView)findViewById(R.id.text);
        ImageLoader.getInstance().displayImage("http://image4.suning.cn/images/shop/cms/4225/1416405971597_1200.jpg",
        		imageView);
        AsyncHttpClient client = new AsyncHttpClient();
        
        client.get("http://www.suning.com/", new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				
			}

        });
    }
}//end class
