package com.michael.biketracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SqlHistory extends Activity implements OnClickListener{
     Button sqlUpdate, sqlopen2view;
     EditText sqlAvSpeed,sqlMaxSpeed,sqlDistance,sqlTime,sqlMtrClimb,sqlCals;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqldatabase);
      
		sqlAvSpeed = (EditText)findViewById(R.id.etSqlAvSpeed);
		sqlTime = (EditText)findViewById(R.id.etSqlTime);
		sqlMtrClimb = (EditText)findViewById(R.id.etSqlMtrsClimbed);
		sqlCals = (EditText)findViewById(R.id.etSqlCalories);
		sqlDistance = (EditText)findViewById(R.id.etSqlDistance);
		sqlMaxSpeed = (EditText)findViewById(R.id.etSqlMaxSpeed);
		
		sqlopen2view = (Button) findViewById(R.id.butnSqlopen2view);
		sqlUpdate = (Button) findViewById(R.id.butnSqlUpdate);
		sqlopen2view.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		
		
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){
		 
		case R.id.butnSqlUpdate:
			
		
			String avspeed = sqlAvSpeed.getText().toString();
			String time = sqlTime.getText().toString();
			String mclimb = sqlMtrClimb.getText().toString();
			String cal = sqlCals.getText().toString();
			String dist = sqlDistance.getText().toString();
			String max = sqlMaxSpeed.getText().toString();
			
			HistoryHandler entry = new HistoryHandler(SqlHistory.this);
			entry.open();
			entry.createEntry( avspeed, time, mclimb, cal, dist, max);
			
			entry.close();
			break;
		
		
		case R.id.butnSqlopen2view:
			break;
		}
	}

}
