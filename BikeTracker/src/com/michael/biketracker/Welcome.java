package com.michael.biketracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Welcome extends Activity{
  @Override
  public void onCreate(Bundle savedInstanceState){
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.welcome);
	  
	 Button goToReg = (Button)findViewById(R.id.registerBtn);
	 Button goToLogIn =(Button)findViewById(R.id.logInBtn);
	 Button goToMenu = (Button)findViewById(R.id.menuBtn);
	
	    goToReg.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent("com.michael.biketracker.Register"));
				
			}
		});
	    goToLogIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent("com.michael.biketracker.LogIn"));
				
			}
		});
	    goToMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent("com.michael.biketracker.MENU"));
				
			}
		});
  }
}
