package com.michael.biketracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;


public class ProfileStart extends Activity {
     int counter,bikeweight,total;
     Button add,sub,addbike,subbike;
     TextView display,displaybike,displaytotal;
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_start);
		counter = 70;
		bikeweight = 10;
		
		add = (Button)  findViewById(R.id.addButton);
		sub = (Button)  findViewById(R.id.subtractButton);
		addbike = (Button)  findViewById(R.id.addBikeWtButton);
		subbike = (Button)  findViewById(R.id.subtractBikeWtButton);
		display = (TextView) findViewById(R.id.textviewDisplay);
	    displaybike = (TextView) findViewById(R.id.textviewBikeWt);
	    displaytotal = (TextView) findViewById(R.id.textviewTotal);
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 counter++;
				 display.setText("Your Weight Is " + counter + "Kg");
				 total = bikeweight + counter;
				 displaytotal.setText("Total Weight Is " + total + "Kg");
			}
		});
	   sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter--;
				 display.setText("Your Weight Is " + counter + "Kg");
				 total = bikeweight + counter;
				 displaytotal.setText("Total Weight Is " + total + "Kg");
			}
		});
	    addbike.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				bikeweight++;
				displaybike.setText("Bike Weight Is " + bikeweight + "Kg");
				total = bikeweight + counter;
				 displaytotal.setText("Total Weight Is " + total + "Kg");
			}
		});
	    subbike.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					bikeweight--;
					displaybike.setText("Bike Weight Is " + bikeweight + "Kg");
					total = bikeweight + counter;
					 displaytotal.setText("Total Weight Is " + total + "Kg");
				}
		});
	   
	}
}
