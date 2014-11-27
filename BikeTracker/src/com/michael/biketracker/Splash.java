package com.michael.biketracker;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {
	
	 MediaPlayer jingle;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
	   jingle = MediaPlayer.create(Splash.this, R.raw.bikebell);
	   
		jingle.start();
		Thread splashTimer = new Thread(){
			public void run(){
				try{
				   sleep(3000);	
				}catch(InterruptedException e){
				e.printStackTrace();	
				}finally{
					Intent openProfileStart = new Intent("com.michael.biketracker.Welcome");
					//Intent openProfileStart = new Intent("com.michael.biketracker.MENU");
				  startActivity(openProfileStart);	
				}
			}
		};
		splashTimer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		jingle.release();
		finish();
	}
    

}
