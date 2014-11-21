package com.xinlan.mainframe;


import lib.imageloader.core.ImageLoader;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

public class MainFrameActivity extends FragmentActivity
{
	private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.image);
        ImageLoader.getInstance().displayImage("http://h.hiphotos.baidu.com/image/pic/item/6c224f4a20a446239e8d311c9b22720e0cf3d70d.jpg",
        		imageView);
    }
}//end class
