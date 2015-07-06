package com.example.ch09_5aldialprog;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

//    protected static final int BUTTON_POSITIVE = -1;
//	protected static final int BUTTON_NEGATIVE = -2;
//	protected static final int BUTTON_NEUTRAL = -3;
    private ProgressDialog proDialog;
    private Handler mHandler = new Handler();

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buildViews();
    }
    
    public void buildViews(){
    	Button btnEdit = (Button)findViewById(R.id.btIdExitEd);
    	btnEdit.setOnClickListener(btnEditListener);
    }
    
    public OnClickListener btnEditListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			AlertDialog.Builder adBuild = 
				new AlertDialog.Builder(MainActivity.this){};
//				adBuild.setTitle(R.string.aldTitle);
//				adBuild.setMessage(R.string.aldPrompt);
//				adBuild.setIcon(R.drawable.save);
//				//設定是否能夠取消
//				adBuild.setCancelable(false);
//				adBuild.setPositiveButton(R.string.btPtPosit, aldBtListener);
//				adBuild.setNegativeButton(R.string.btPtNeg, aldBtListener);
//				adBuild.setNeutralButton(R.string.btPtNeut, aldBtListener);
//				adBuild.show();
				
				//chaining of calls:因為大家都回傳 Build，因此可以一直dot(.)下去。 
				adBuild.setTitle(R.string.aldTitle)
				.setMessage(R.string.aldPrompt)
				.setIcon(R.drawable.save)
				//設定點擊設備上的返回鍵時，是否能夠取消。
				.setCancelable(false)
				.setPositiveButton(R.string.btPtPosit, aldBtListener)
				.setNegativeButton(R.string.btPtNeg, aldBtListener)
				.setNeutralButton(R.string.btPtNeut, aldBtListener)
				.show();				
		}
	};
	
	private DialogInterface.OnClickListener aldBtListener = 
		new DialogInterface.OnClickListener() {
					
		@Override
		public void onClick(DialogInterface dialog, int id) {
			
			String st = new String();
			
			switch(id){
			case AlertDialog.BUTTON_POSITIVE:
				st = "您按了 '是'鈕 ，將會儲存檔案並結束編輯!";
				//建構一個進度對話方塊。
				proDialog = new ProgressDialog(MainActivity.this);
				proDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//				proDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); //環狀進度
				proDialog.setMessage("正在儲存檔案中，請稍候...");
				proDialog.setMax(100);
				proDialog.show();
				showProgress(); 
				break;
			case AlertDialog.BUTTON_NEGATIVE:
				st = "您按了'否'鈕，將不會儲存檔案並結束編輯!";
				MainActivity.this.finish();
				break;
			case AlertDialog.BUTTON_NEUTRAL:
				st = "您按了'取消'鈕，將取消結束編輯並回到編輯模式!";
				break;
			}
			
			Toast.makeText(MainActivity.this, st, Toast.LENGTH_SHORT).show();
		}		
	};
	
	private void showProgress() {
		new Thread(new Runnable(){

			@Override
			public void run() {
				//設定開始的時間
				Calendar begin = Calendar.getInstance();
				do{
					//取得目前的時間
					Calendar now = Calendar.getInstance();
					//計算時間差
					final int DiffSec = 60 * (now.get(Calendar.MINUTE) -
						begin.get(Calendar.MINUTE)) +
						now.get(Calendar.SECOND) -
						begin.get(Calendar.SECOND);
					
					if(DiffSec * 4 > 100){
						mHandler.post(new Runnable(){

							@Override
							public void run() {
								proDialog.setProgress(100);
							}
						});
						break;
					}
					
//					if(DiffSec * 2 > 100){
//						proDialog.setProgress(100);
//						break;
//					}
					
					mHandler.post(new Runnable(){

						@Override
						public void run() {
							proDialog.setProgress(DiffSec * 4);
						}
					});
					
//					proDialog.setProgress(DiffSec * 2);

				}while(true);
				
				proDialog.cancel();
				MainActivity.this.finish();
			}
		}).start();
	}
}// end class MainActivity.
