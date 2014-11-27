package com.michael.biketracker;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
//import android.util.Log;
import android.widget.Toast;

public class TestMap extends Activity implements LocationListener {
     private GoogleMap googlemap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isGooglePlay()){  //check if google play services is available..if it is set up the map
        	 setContentView(R.layout.testmap);
        	 setUpMap();
        }
       
    }
    private boolean isGooglePlay(){
    	int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    	if (status == ConnectionResult.SUCCESS)
    	{
    		return(true);
    	}                         //if googleplay not available toast message that not available
    	else
    	{
    		Toast.makeText(this, "google play not available", Toast.LENGTH_LONG).show();
    	}
    	return(false);
    }
    
    private void setUpMap(){
    	if (googlemap == null){
    		googlemap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
    		if(googlemap != null){
    			googlemap.setMyLocationEnabled(true);
    			LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);//get current location through Location Service
    			String provider = locMan.getBestProvider(new Criteria(), true);
    			if(provider == null){
    				onProviderDisabled(provider);
    			}
    			Location loc = locMan.getLastKnownLocation(provider);//even if system provider is null get last known location
    			if (loc != null){
    				onLocationChanged(loc);//call the on location changed method
    			}
    			//onclick listener for google map
    			googlemap.setOnMapLongClickListener(onLongMapClick());
    		}
    	}
    }
    
    
	private OnMapLongClickListener onLongMapClick() {
		//method called when long click on map 
		return new OnMapLongClickListener(){

			@Override
			public void onMapLongClick(LatLng arg0) {
				// TODO Auto-generated method stub
			//	Log.i(arg0.toString(),"long click made");
			}
			
		};
	}
	
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		LatLng latlng = new LatLng(location.getLatitude(),location.getLongitude());
		googlemap.moveCamera(CameraUpdateFactory.newLatLng(latlng));//googlemap moves to current location
		googlemap.animateCamera(CameraUpdateFactory.zoomTo(10));   // googlemap zooms in
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}