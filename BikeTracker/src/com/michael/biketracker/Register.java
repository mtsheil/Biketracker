package com.michael.biketracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;



public class Register extends Activity {
	
	 Button chekCmd;
	 EditText inputUser;
	 EditText inputPass;
	 EditText inputPass2;
	 TextView dsplyTxt;
	 Button goToMenu;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		//button to go to second activity
	changePage();
	
         
	 
	 verifyDetails();

		

	    chekCmd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String compareUser = inputUser.getText().toString();
				String comparePass = inputPass.getText().toString();
				String comparePass2 = inputPass2.getText().toString();
				
				dsplyTxt.setText(compareUser);
                if(compareUser.contentEquals("")){
                	dsplyTxt.setText("UserName cannot be blank");
                }else if(comparePass.contentEquals("")){
                	dsplyTxt.setText("Password cannot be be blank");
                }else if(comparePass2.contentEquals("")){
                	dsplyTxt.setText("Repeat Password cannot be be blank");
                }else if(comparePass.contentEquals(comparePass2)){
                //	dsplyTxt.setText("Proceed to menu page");
                	Intent openProfileMenu = new Intent("com.michael.biketracker.MENU");
  				  startActivity(openProfileMenu);
                }else {
                	dsplyTxt.setText("Passwords must match");
                }
                
			}
		}
	    );
	    goToMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent("com.michael.biketracker.Menu"));
				
			}
		});
     }

	private void changePage() {
		goToMenu = (Button)findViewById(R.id.butnSwitch);
		
	}

	private void verifyDetails() {
		  chekCmd = (Button)findViewById(R.id.butnEnter);
	      inputUser = (EditText) findViewById(R.id.editxtUser);
	      inputPass = (EditText) findViewById(R.id.editxtPassword);
	      inputPass2 = (EditText) findViewById(R.id.editxtPassRepeat);
	      dsplyTxt = (TextView) findViewById(R.id.txtViewResults);
		
	}
}