package com.michael.biketracker;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DialogActivity2 extends Activity {
    CharSequence[] items = { "Road Bike", "Hybrid Bike", "Mountain Bike" };
    boolean[] itemsChecked = new boolean [items.length];
	
 
	
    
    /**called when the activity is first created*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
	}

	@SuppressWarnings("deprecation")
	public void onClick(View v){
		showDialog(0);
	}

	@Override
	protected Dialog onCreateDialog(int id){
		switch(id){
		case 0:
			return new AlertDialog.Builder(this)
			.setIcon(R.drawable.ic_launcher)
			.setTitle("Select your style of Bike")
			.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton)
						{
							Toast.makeText(getBaseContext(),
									"Ok clicked!", Toast.LENGTH_SHORT).show();
						
							
						}
					}
			)
			.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int whichButton)
				        { Toast.makeText(getBaseContext(),
				        			"Cancel Clicked!", Toast.LENGTH_SHORT).show();}
	        		}
	    	)
	    	.setMultiChoiceItems(items, itemsChecked,
	    	   new DialogInterface.OnMultiChoiceClickListener() {
			     public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					   Toast.makeText(getBaseContext(),
							   items[which] + (isChecked ? " checked! " : " unchecked!") ,
							   Toast.LENGTH_SHORT).show();
					
				}
			}
			
		).create();
		}
		return null;
	}
    
}


