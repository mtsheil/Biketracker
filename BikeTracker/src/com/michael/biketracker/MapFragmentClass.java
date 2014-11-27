package com.michael.biketracker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapFragmentClass extends Fragment {
	
	private GoogleMap googlemap;
	private static final LatLng CELBRIDGE = new LatLng(53.339555, -6.538801);
	
	public void processMap(View v)
	{
	if(googlemap == null)	
	{
		googlemap = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapid)).getMap();
	}
	if(googlemap != null)
	{
	    googlemap.addMarker(new MarkerOptions( ).position(CELBRIDGE).title("This is celbridge"));
	    googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(CELBRIDGE, 13));
	}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.map_fragment_layout,container,false);
		processMap(v);
		return v;
	}

}
