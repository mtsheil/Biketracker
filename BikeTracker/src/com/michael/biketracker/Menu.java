package com.michael.biketracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity{
	
	String classes[] = {"ProfileStart","Splash","Text","LogIn","GPS","Map","DialogActivity2","Welcome","TestMap","GPS_MAP","SqlHistory","HistoryView"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		// TODO Auto-generated method stub
		String menuPlace = classes[position];
		super.onListItemClick(l, v, position, id);
		try{
		Class myClass = Class.forName("com.michael.biketracker." + menuPlace );
		Intent IntentStartMenu = new Intent(Menu.this, myClass);
		startActivity(IntentStartMenu);
	}catch(ClassNotFoundException e){
		e.printStackTrace();
	}
  }
}