package com.michael.biketracker;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.view.View.OnClickListener;

public class Map extends Activity{
	Button SHOWMAP;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.mapactivity);
		 SHOWMAP  = (Button) findViewById(R.id.showMap);
		 SHOWMAP.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager FM = getFragmentManager();
				FragmentTransaction FT = FM.beginTransaction();
                MapFragmentClass MF = new MapFragmentClass();
                FT.add(R.id.maplayout, MF);
                FT.commit();
			}
		});
	
	}

	
	
	
	
	
}
