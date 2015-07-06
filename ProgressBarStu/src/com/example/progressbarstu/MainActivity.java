package com.example.progressbarstu;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ProgressBar progressBar = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        
//        Thread thread = new Thread(new Runnable() {
//	       
//	       @Override
//	       public void run() {
//              int progressBarMax =progressBar.getMax();
//              try {
//                 while(progressBarMax!=progressBar.getProgress())
//                 {
//                    int stepProgress = progressBarMax/10;
//                    int currentprogress = progressBar.getProgress();
//                    progressBar.setProgress(currentprogress+stepProgress);
//                    Thread.sleep(1000);
//                    //1000毫秒  = 1秒
//                 }
//                 
//              } catch(InterruptedException e) {
//                 // TODO Auto-generatedcatch block
//                 e.printStackTrace();
//              }
//	       }
//        });
//        
//        thread.start();
        
        // 非thread版的 更新進度
        while(true)
        {
	        int progressBarMax = progressBar.getMax();
	        int stepProgress = progressBarMax/10;
	        int currentprogress = progressBar.getProgress();
	        progressBar.setProgress(currentprogress+stepProgress);
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        if(currentprogress >= progressBarMax)
	        	break;
        }
        
        final TextView tv2 = (TextView)findViewById(R.id.textView2);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "hi", Toast.LENGTH_SHORT).show();
				tv2.setText((new Date()).toString());
			}
		});
    }
  
}
