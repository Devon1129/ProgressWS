package com.example.progressbartest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	private ProgressBar myProgressBar;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //僅僅 test顯示 progressBar狀態貌。 
        myProgressBar = (ProgressBar)findViewById(R.id.progressbar_updown);
        //ProgressBar進度值增加5
        myProgressBar.incrementProgressBy(5);
        //ProgressBar進度值減少5
        myProgressBar.incrementProgressBy(-5);
        
        myProgressBar.incrementSecondaryProgressBy(5);
        myProgressBar.incrementSecondaryProgressBy(-5);
    }
  
}
