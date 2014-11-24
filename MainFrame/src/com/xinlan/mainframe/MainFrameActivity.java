package com.xinlan.mainframe;

import java.io.File;
import java.io.FileNotFoundException;

import lib.asynchronous.AsyncHttpResponseHandler;
import lib.asynchronous.RequestParams;
import lib.asynchronous.TextHttpResponseHandler;
import lib.imageloader.core.DisplayImageOptions;
import lib.imageloader.core.ImageLoader;
import lib.niftymodaldialogeffects.Effectstype;
import lib.niftymodaldialogeffects.NiftyDialogBuilder;
import lib.picturechooser.SelectPictureActivity;
import org.apache.http.Header;
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
	public static final int UPLOAD_IMAGE = 8;
	private ImageView imageView;
	private TextView textView;
	private View selectBtn;
	private View showDialog;
	private MainFrameActivity context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
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

//		NetClient netClient = new NetClient();
//
//		RequestParams params = new RequestParams();
//		params.add("hello", "world");
//		netClient.post(this,
//				"http://10.24.64.78:8080/Demo1/servlet/PhoneRequest", params,
//				new TextHttpResponseHandler() {
//					@Override
//					public void onFailure(int statusCode, Header[] headers,
//							String responseString, Throwable throwable) {
//						System.out.println("error---->" + responseString);
//					}
//
//					@Override
//					public void onSuccess(int statusCode, Header[] headers,
//							String data) {
//						System.out.println("data---->" + data);
//					}
//				});

		selectBtn.setOnClickListener(new UploadFileClick());
		showDialog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogShow(v);
			}
		});
	}

	private final class UploadFileClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			context.startActivityForResult(new Intent(context,
					SelectPictureActivity.class), UPLOAD_IMAGE);
		}
	}// end inner class

	private final class SelectClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			context.startActivityForResult(new Intent(context,
					SelectPictureActivity.class), ADD_IMAGE);
		}
	}// end inner class

	protected void onActivityResult(int request, int result, final Intent data) {
		if(result == RESULT_OK ){
			switch(request)
			{
			case ADD_IMAGE:
				String path = data.getStringExtra("imgPath");
				System.out.println("path---->" + path);
				break;
			case UPLOAD_IMAGE:
				uploadImage(data.getStringExtra("imgPath"));
				break;
			}//end switch
		}
	}
	
	/**
	 * 文件上传
	 * @param filepath
	 */
	private void uploadImage(String filepath)
	{
		File uploadFile = new File(filepath);
		RequestParams params = new RequestParams();
		try {
			params.put("image", uploadFile);
			//params.put("file", new File("/storage/emulated/0/libgdx-1.2.0.zip"));
			///storage/emulated/0/libgdx-1.2.0.zip
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		params.put("v1", "草泥马");
		params.put("v2", filepath);
		NetClient.post("http://10.24.64.52:8080/Demo1/upload", params, new TextHttpResponseHandler() {

			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				super.onProgress(bytesWritten, totalSize);
				System.out.println("Progress="+bytesWritten+"    "+totalSize);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				Toast.makeText(context, "上传失败!"+responseString, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				Toast.makeText(context, "上传成功!", Toast.LENGTH_LONG).show();
			}
		});
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
