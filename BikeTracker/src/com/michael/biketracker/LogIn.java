package com.michael.biketracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends Activity {
	
	Button chekCmd;
	EditText inputUser;
	EditText inputPass;
	TextView dsplyTxt;
	Button goToMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		
		  checkDetails(); 
	      changePage();
		  
           chekCmd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String compareUser = inputUser.getText().toString();
				String comparePass = inputPass.getText().toString();
			
			    String username = "john";
			    String password = "smith" ;
			 
				
				dsplyTxt.setText(compareUser);
                if(compareUser.contentEquals("")){
                	//dsplyTxt.setText("UserName cannot be blank");
                	 { Toast.makeText(getBaseContext(),
			        			"User name cannot be blank", Toast.LENGTH_SHORT).show();}
                }else if(comparePass.contentEquals("")){
                	//dsplyTxt.setText("Password cannot be be blank");
                	 { Toast.makeText(getBaseContext(),
			        			"Password cannot be blank", Toast.LENGTH_SHORT).show();}
               }else if(comparePass.contentEquals(password) && compareUser.contentEquals(username)){
              // 	dsplyTxt.setText("Proceed to menu page");
            	Intent openProfileMenu = new Intent("com.michael.biketracker.MENU");
				  startActivity(openProfileMenu);
                }else {
                	//dsplyTxt.setText("UserName and password not Recognised");
                	  Toast.makeText(getBaseContext(),
                			 "UserName and password not Recognised", Toast.LENGTH_SHORT).show();
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

	private void checkDetails() {
		  chekCmd = (Button)findViewById(R.id.butnEnter1);
	      inputUser = (EditText) findViewById(R.id.editxtUser1);
	      inputPass = (EditText) findViewById(R.id.editxtPassword1);
	      dsplyTxt = (TextView) findViewById(R.id.txtViewResults1);
	}

	private void changePage() {
		
		goToMenu = (Button)findViewById(R.id.butnSwitch);
	}
}