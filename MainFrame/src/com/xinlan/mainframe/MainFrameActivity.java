package com.xinlan.mainframe;

import lib.asynchronous.RequestParams;
import lib.asynchronous.TextHttpResponseHandler;
import lib.imageloader.core.DisplayImageOptions;
import lib.imageloader.core.ImageLoader;
import lib.niftymodaldialogeffects.Effectstype;
import lib.niftymodaldialogeffects.NiftyDialogBuilder;
import lib.picturechooser.SelectPictureActivity;

import org.apache.http.Header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xinlan.mainframe.network.NetClient;

public class MainFrameActivity extends FragmentActivity {
	public static final int ADD_IMAGE = 7;
	private ImageView imageView;
	private TextView textView;
	private View selectBtn;
	private View showDialog;
	private MainFrameActivity context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context =this;
		imageView = (ImageView) findViewById(R.id.image);
		textView = (TextView) findViewById(R.id.text);
		selectBtn = findViewById(R.id.select_pic);
		showDialog = findViewById(R.id.show_dialog);
		
		ImageLoader
				.getInstance()
				.displayImage(
						"http://sale.suning.com/images/advertise/011007/20141114cwsdsn/images/20141117chuwei_01.jpg",
						imageView,
						new DisplayImageOptions.Builder().showImageOnLoading(
								R.drawable.ic_launcher).build());

		NetClient netClient = new NetClient();

		RequestParams params = new RequestParams();
		params.add("hello", "world");
		netClient.post(this,
				"http://10.24.64.78:8080/Demo1/servlet/PhoneRequest", params,
				new TextHttpResponseHandler() {
					@Override
					public void onFailure(int statusCode, Header[] headers,
							String responseString, Throwable throwable) {
						System.out.println("error---->" + responseString);
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							String data) {
						System.out.println("data---->" + data);
					}
				});

		selectBtn.setOnClickListener(new SelectClick());
		showDialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogShow(v);
			}
		});
	}

	private final class SelectClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			context.startActivityForResult(new Intent(context,SelectPictureActivity.class), 
					ADD_IMAGE);
//			MainFrameActivity.this.startActivityForResult(
//					new Intent(MainFrameActivity.this, SelectPictureActivity.class)
//							// following 3 lines are optional
//							.putExtra("crop", true).putExtra("aspectX", 16)
//							.putExtra("aspectY", 9), ADD_IMAGE);
			
		}
	}// end inner class

	protected void onActivityResult(int request, int result, final Intent data) {
		if (result == RESULT_OK && request == ADD_IMAGE) {
			String path = data.getStringExtra("imgPath");
			System.out.println("path---->" + path);
		}
	}

	public void dialogShow(View v) {
		NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
		Effectstype effect = Effectstype.Slideright;
		dialogBuilder.withTitle("Modal Dialog")
		// .withTitle(null) no title
				.withTitleColor("#FFFFFF")
				// def
				.withDividerColor("#11000000")
				// def
				.withMessage("This is a modal Dialog.")
				// .withMessage(null) no Msg
				.withMessageColor("#FFFFFFFF")
				// def | withMessageColor(int resid)
				.withDialogColor("#FFE74C3C")
				// def | withDialogColor(int resid) //def
				.isCancelableOnTouchOutside(true) // def | isCancelable(true)
				.withDuration(700) // def
				.withEffect(effect) // def Effectstype.Slidetop
				.withButton1Text("OK") // def gone
				.withButton2Text("Cancel") // def gone
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(v.getContext(), "i'm btn1",
								Toast.LENGTH_SHORT).show();
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// Toast.makeText(v.getContext(), "i'm btn2",
						// Toast.LENGTH_SHORT).show();
						
					}
				}).show();
	}
}// end class
