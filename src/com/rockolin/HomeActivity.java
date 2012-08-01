package com.rockolin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class HomeActivity extends Activity{
	private Thread thread;
	private Activity tview = this;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
	    thread =  new Thread(){
	        @Override
	        public void run(){
	            try {
	                synchronized(this){
	                    wait(3000);
	                }
	            }
	            catch(InterruptedException ex){                    
	            }
	            
	            Intent intent = new Intent(tview, DashboardActivity.class);
	            startActivity(intent);
	        }
	    };

	    thread.start();        
	}

	@Override
	public boolean onTouchEvent(MotionEvent evt)
	{
	    if(evt.getAction() == MotionEvent.ACTION_DOWN)
	    {
	        synchronized(thread){
	            thread.notifyAll();
	        }
	    }
	    return true;
	}    
}
