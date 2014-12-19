package com.michael.biketracker;    //SQLView

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HistoryView extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		TextView tv = (TextView) findViewById(R.id.txtVuSQLinfo);
		HistoryHandler infor = new HistoryHandler(this);
		infor.open();
		String data = infor.getData();
		infor.close();
		tv.setText(data);
	}

}
