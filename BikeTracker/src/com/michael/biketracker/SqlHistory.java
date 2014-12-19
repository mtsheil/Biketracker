package com.michael.biketracker;   //Sqliteexample

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SqlHistory extends Activity implements OnClickListener{
     Button sqlUpdate, sqlopen2view, sqlGetInfo, sqlMod, sqlDelete;
     EditText sqlAvSpeed,sqlMaxSpeed,sqlDistance,sqlTime,sqlMtrClimb,sqlCals,sqlrowId;
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
		sqlrowId = (EditText)findViewById(R.id.etRowId);
		
		sqlopen2view = (Button) findViewById(R.id.butnSqlopen2view);
		sqlUpdate = (Button) findViewById(R.id.butnSqlUpdate);
		sqlGetInfo = (Button) findViewById(R.id.btnsqlGetInfo);
		sqlMod = (Button) findViewById(R.id.btnsqlMod);
		sqlDelete = (Button) findViewById(R.id.btnsqlDelete);
		
		sqlopen2view.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlMod.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
		
		
		
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()){
		 
		case R.id.butnSqlUpdate:
			
		    boolean testOk = true;
			try{
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
			
		    }catch (Exception e){
		    	    testOk = false;
		    	    String error = e.toString();
		    	    Dialog d = new Dialog(this);
		    		d.setTitle("Problem");
		    		TextView tv = new TextView(this);
		    		tv.setText(error);
		    		d.setContentView(tv);
		    		d.show();
		    }finally{
		    	if(testOk){
		    		Dialog d = new Dialog(this);
		    		d.setTitle("WorksOk");
		    		TextView tv = new TextView(this);
		    		tv.setText("works");
		    		d.setContentView(tv);
		    		d.show();
		    		
		    		
		    	}
		    }
			break;
		
		
		case R.id.butnSqlopen2view:
			Intent i = new Intent("com.michael.biketracker.HistoryView");
			startActivity(i);
			break;
			
			
			/*	case R.id.btnsqlGetInfo:
			String st = sqlrowId.getText().toString();
			long l = Long.parseLong(st);
			HistoryHandler hh = new HistoryHandler(this);
			hh.open();
			String returnedDist = hh.getDist(l);
			String returnedTime = hh.getTime(l);
			String returnedAv = hh.getAv(l);
			String returnedMax = hh.getMax(l);
			String returnedClimb = hh.getClimb(l);
			String returnedCal = hh.getCal(l);
			
			sqlDistance.setText(returnedDist);
			sqlTime.setText(returnedTime);
			sqlAvSpeed.setText(returnedAv);
			sqlMaxSpeed.setText(returnedMax);
			sqlMtrClimb.setText(returnedClimb);
			sqlCals.setText(returnedCal);
			
			break;   */
			
			
		case R.id.btnsqlMod:
			break;
			
			
		case R.id.btnsqlDelete:
			break;
			
		}
	}

}
