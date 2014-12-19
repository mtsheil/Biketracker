package com.michael.biketracker;

//import android.annotation.TargetApi;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import android.location.Criteria;

import android.app.Dialog;



//@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class GPS_MAP extends Activity implements LocationListener,OnClickListener {
	
	int distint;
	int counter = 0;
	int timeCount = 0;
	int mins;
	int seconds;
	int hours;
	double height ;
    float lastHeight ;
    float countHeight = 0;
	float heightDiff;
	float currentSpeed;
	float currentSpeedKm;
	float maxsp = 0;
	String speed, calStr, mx,avs;
//	TextView notNull;//show if connected to satellite or not
	TextView tvDist;
	TextView txtvukm;
	TextView tvMaxSp;
	TextView timer;
	TextView tvAscent;
	TextView tvAvSpeed;
	float[] result;  //float array to store the distance
	float countDist=0;
	float distKm =0;
    float distance=0;
    double lat1=0, lon1=0 ,lat2 = 0, lon2 = 0;
	int dist=0;
	float avSpeed;
	float calories = 0;
	private GoogleMap googlemap;
	Button bstart,bpause,bstop;
	// String calr, heit, timr;
    String ds,dkm;
    String sqldist,sqltime,sqlmax;
	  protected LocationManager locMgr;
	 


	   
	WakeLock wkLok;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		//wake-lock- keeps app from going into sleep mode
		PowerManager pwrMgr = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wkLok = pwrMgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "StayAwake");
		
		super.onCreate(savedInstanceState);
		//wkLok.acquire();
	
		setContentView(R.layout.testmap_small);
        if(isGooglePlay()){  //check if google play services is available..if it is set up the map
       	 setUpMap();
       }
	}
	
	public void onStart()	{
		super.onStart();
		wkLok.acquire();
		locMgr =(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4657,1, this);//1000 Milliseconds and 1 meters distance
	   this.onLocationChanged(null);
		

	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//inflate the menu; this adds items to the action bar if it is present
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		

	}*/


	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	@Override
	public void onLocationChanged(Location location) {
		
//       notNull =(TextView) this.findViewById(R.id.notNull);// textView show if connected to satellite or not
		 tvDist =(TextView) this.findViewById(R.id.txtVuDistance);
		 txtvukm =(TextView) this.findViewById(R.id.txtVuSpdKm);
		 tvMaxSp =(TextView) this.findViewById(R.id.txtVuMaxSpeed);
	     tvAscent = (TextView) this.findViewById(R.id.tvAscent);
	     timer = (TextView) this.findViewById(R.id.tvTimer);
	     tvAvSpeed = (TextView) this.findViewById(R.id.tvAvr);
	     bstart = (Button)findViewById(R.id.btnStart);
	     bpause = (Button)findViewById(R.id.btnPause);
	     bstop = (Button)findViewById(R.id.btnStop);
	     bstart.setOnClickListener(this);
	     bpause.setOnClickListener(this);
	     bstop.setOnClickListener(this);
	



   
	     
		if(location==null)
		{   //display null values initially
		//	notNull.setText("Getting Satellite Fix");
			txtvukm.setText("_._ Km/h");
			tvDist.setText("_._ m");
			timer.setText("_._._");
			tvAvSpeed.setText("_._ Km/h");
			tvMaxSp.setText("_._ Km/h");
			tvAscent.setText("_ m");

			
		}
		else if(location != null) 
		{ //  notNull.setText("running");
			//display current values as device moves and round to 1 place of decimals
			
	    	 currentSpeed = location.getSpeed();
		     showSpeedKm();// current speed in km
		     
		     timerCount();//elapsed time
			 
		        
	         showMaxSp(); //maximum speed
	         
	         height = location.getAltitude();

	         
	         addHeight();//meters climbed
	         
	        lat1 = location.getLatitude(); //distance
	        lon1 = location.getLongitude();
	        
	        getDistance();
	        
	        getAv();
	        
    	}
	
	}
	private String getAv(){
        if (dist >1 & timeCount>1) //divide distance by time to calculate average speed
        { avSpeed = (float) ((dist/timeCount)*3.6);
    	 avs = String.format( "%.1f", avSpeed);
		tvAvSpeed.setText(avs + " Km/h");}
		return avs;
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

	public int getDistance() {  //method to calculate distance
		if(lat2 != 0 & lon2!=0) // if second latitude and longitude is not zero. ie location has changed to 2nd location
   	{float[] results =new float[1];
   	Location.distanceBetween(lat1, lon1, lat2, lon2, results);
   	 distance = results[0];
   	 countDist =  distance + countDist;
       dist = (int) countDist; 
       distKm =countDist/1000;//cast distance to integer to ignore decimals 
   //   distint = (int) distKm;
       if(dist<1000)  // if distance is less than  1km then display as meters
            { tvDist.setText(dist + "m");//Display whole number of meters
            
            }
       else if (dist > 999)            //when distance is greater than 1000 meters
      	  {   // then display as kilometres
           ds = String.format("%.1f", distKm);// Display  km to 1 place of decimals
      	  tvDist.setText(ds + "Km");
      	  
      	  }
       }
      lat2 = lat1;//make second location first location
      lon2 = lon1;

	return dist; 
		
	}
	
	public String sqlDistance(int dist){
		if (dist < 1000){
			String dist1 = String.valueOf(dist);
			 sqldist = dist1.concat("m  .");}
		else if(dist>999){
			float dist2 = dist/1000;
			dkm =String.format("%.1f", dist2);
			sqldist = dkm.concat("km  .");
		}
			
		return sqldist;
	}

	private int timerCount() {
		
		timeCount +=5;// counter to get elapsed time. Only increments when location has changed.
		if(timeCount<10)
		{timer.setText("0" + timeCount + "s" );}	
		else if (timeCount <60)                 // handling display of elapsed time when less than one minute
		{timer.setText(timeCount + "s");}      //display seconds
		else if (timeCount > 59 && timeCount < 3600) // handling display of time when less one hour and more than one minute
		{ mins = timeCount/60;                       //calculate minutes
		seconds = timeCount%60;                      //calculate seconds
		timer.setText(mins +":" + seconds + "");}   //display  minutes:seconds    
		else if(timeCount > 3599)                               //handling display of time when more than one hour
		{hours = timeCount/3600;                                   //calculate hours
		mins = (
				timeCount%3600)/60;                                 //calculate minutes
		seconds = timeCount%60;                                      //calculate seconds
		timer.setText( hours + ":" + mins + ":"+ seconds + "");   //display  hours:minutes:seconds
		} 
		return timeCount;
	}
	
	public String sqlTimer(int timeCount){
		if (timeCount<60){
			String t1 = String.valueOf(timeCount);
			sqltime = "0:00:"+t1;}
		else if(timeCount> 59 && timeCount < 3600){
			int mt=timeCount/60;
			String m = String.valueOf(mt);
			int st = timeCount%60;
			String s = String.valueOf(st);
			sqltime = "0:" + m + ":" +s;}
		else if(timeCount>3599){
			int h = timeCount/3600;
			String hs = String.valueOf(h);
			int min = (timeCount-(3600*h))%60;
			String ms = String.valueOf(min);
			int sec =(timeCount-(3600*h))%3600;
			String ss = String.valueOf(sec);
			sqltime = hs + ":" + ms+ ":" + ss ;
		}
			
		return sqltime;
	}

	private void addHeight() {//method to calculate accumulated altitude
		if(height - lastHeight  < 2  &&  height > lastHeight )//if height difference is less than 2 m . this is to avoid a large initial value where the lastHeight 
		{ heightDiff =     (float) (height - lastHeight  )  ;// has a zero initial value causing heightDiff to be equal to the initial altitude. It also has the effect 
			countHeight = countHeight + heightDiff;          //of smoothing out altitude spikes. && checks that new height is higher than last height
			tvAscent.setText((int) countHeight + " m");//  countHeight is cast to an int to eliminate figures to right of decimal point
		//	heit =String.format( "%.1f", countHeight);
			}
		lastHeight =  (float) height;
	}



    private String showMaxSp() {//method to get maximum speed. current speed is set to max speed if it is greater than current max speed
		if(currentSpeedKm > maxsp)
		{maxsp = currentSpeedKm;}
         mx	 = String.format( "%.1f", maxsp);
		tvMaxSp.setText(mx + " Km/h");
		 sqlmax = mx + "kph";
	return sqlmax;
	}

	public void showSpeedKm() {
		// method to show current speed
		 currentSpeedKm = (float) (currentSpeed*3.6); //multiply by 3600 to convert to hours and divide by 1000 to get kilometers
		   //show speed in kilometres per hour to one place of decimals
		 speed = String.format("%.1f", currentSpeedKm);//from http://stackoverflow.com/questions/11072340/i-need-to-round-a-float-to-two-decimal-places-in-java
		txtvukm.setText(speed + " Km/h");
		
	}

	public float getCals(int dist, float countHeight) {
		// method to calculate calories
		calories = (dist*(25/1000)+ countHeight) ;
	 //   calr = String.format("%.1f", calories);
		return calories;
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wkLok.release();
		stopUsingGPS();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		onDestroy();
	}
	 
    public void onClick(View v){
    	switch(v.getId()){
    	
    	    case R.id.btnStart:
    	    	onStart();
    	    { Toast.makeText(getBaseContext(),
        			"Start pressed", Toast.LENGTH_SHORT).show();}	
    	    break;	
    	    case R.id.btnPause:
    	    	onPause();
    	    { Toast.makeText(getBaseContext(),
        			"pause pressed", Toast.LENGTH_SHORT).show();}
    	    break;
    	  
    	    
    	    case R.id.btnStop:
    	   
    	    	onStop();
    	 /*  	getCals( dist,  countHeight);
    	    	timerCount();
    	    	addHeight();
    	    	int heightshow = (int) countHeight;
    	    	getDistance();
    	 	distint =(int) (dist/1000);
    	    	showMaxSp();
    	    	getAv();*/
    	    	sqlDistance(dist);
    	    	sqlTimer( timeCount);
    	    	showMaxSp();
    	    	
    	    	 { Toast.makeText(getBaseContext(),
    	        		"stop pressed", Toast.LENGTH_SHORT).show();}  	
    	    	
    		    boolean testOk = true;
    			try{
    			String avspeed = "avs";
    			String time = sqltime;
    			String mclimb = "heit";
    			String cal = "calr";
    			String dist =sqldist;
    			String max = sqlmax;
    			
    			HistoryHandler entry = new HistoryHandler(GPS_MAP.this);
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
   	    	
    	    	
    	//  { Toast.makeText(getBaseContext(),
      //	"Av="+ avs +	"calories="+ calories + "distance=" + dist , Toast.LENGTH_LONG).show();}
    	  //  { Toast.makeText(getBaseContext(),
    	  //      "time="+ timeCount+	"calories="+ calories + "height" + heightshow , Toast.LENGTH_LONG).show();}
    	    break;	
    	    
    	
    	}
    }
    public void stopUsingGPS(){//http://www.androidhive.info/2012/07/android-gps-location-manager-tutorial/
        if(locMgr != null){
        	locMgr.removeUpdates(this);
        }       
    }
	
}

