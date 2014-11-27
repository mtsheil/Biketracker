package com.michael.biketracker;

//import android.annotation.TargetApi;
import android.R.string;
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
import android.widget.TextView;


//@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class GPS extends Activity implements LocationListener {
	
	
	int counter = 0;
	int timeCount = 2;
	int mins;
	int seconds;
	int hours;
	double height ;
    float lastHeight ; //= (float) height;
    float countHeight = 0;
	float heightDiff;
	float currentSpeed;
	float currentSpeedKm;
	float maxsp = 0;
	String speed;
	TextView notNull;//show if connected to satellite or not
	TextView tvDist;
	TextView txtvukm;
	TextView tvMaxSp;
	TextView tvAlt;
	TextView timer;
	TextView tvAscent;
	TextView avspeed;
	TextView latitude;
	TextView longitude;
	/*   float[] result;
       double lat1, lon1, lat2, lon2;
       String distance;
       double finalDistance1;
       double finalDistance2;*/
 /*    double lat1, lon1;
       String distance;
       double finalDistance1;
       double finalDistance2;
       double lat2 = 0, lon2 = 0;*/
	
	WakeLock wkLok;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		//wake-lock- keeps app from going into sleep mode
		PowerManager pwrMgr = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wkLok = pwrMgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "StayAwake");
		
		super.onCreate(savedInstanceState);
		wkLok.acquire();
		setContentView(R.layout.gps);
		
		LocationManager locMgr =(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,10, this);//1000 Milliseconds and 2 meters distance
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
		
		 notNull =(TextView) this.findViewById(R.id.notNull);// textView show if connected to satellite or not
		 tvDist =(TextView) this.findViewById(R.id.txtVuDistance);
		 txtvukm =(TextView) this.findViewById(R.id.txtVuSpdKm);
		 tvMaxSp =(TextView) this.findViewById(R.id.txtVuMaxSpeed);
		 tvAlt =(TextView) this.findViewById(R.id.tVAltitude);
	     tvAscent = (TextView) this.findViewById(R.id.tvAscent);
	     timer = (TextView) this.findViewById(R.id.tvTimer);
	     avspeed = (TextView) this.findViewById(R.id.tvAvr);
	     latitude = (TextView) this.findViewById(R.id.currentLat);
	 	 longitude = (TextView)this.findViewById(R.id.currentLon);
	
     
	     
		if(location==null)
		{   //display null values initially
			notNull.setText("Getting Satelite Fix");
			txtvukm.setText("_._ Km/h");
			tvDist.setText("_._ Km");
			timer.setText("_._._sec");
			avspeed.setText("_._ Km/h");
			tvMaxSp.setText("_._ Km/h");
			tvAlt.setText("_ meters");
			tvAscent.setText("_ meters");
			latitude.setText("_ ");
			longitude.setText("_ ");
			
		}
		else if(location != null) 
		{   notNull.setText("running");
			//display current values as device moves and round to 1 place of decimals
			
	    	 currentSpeed = location.getSpeed();
		     showSpeedKm();// current speed in km
		     
		     //distance
		
			 
			 timerCount();//elapsed time
			 
			 //average speed
	        
	         showMaxSp(); //maximum speed
	         
	         height = location.getAltitude();
		     showHeight();// current altitude
	         
	         addHeight();//meters climbed
	         
	         //Display current location   
	         latitude.setText("Current Latitude: " + String.valueOf(location.getLatitude())); 
	         longitude.setText("Current Longitude: " + String.valueOf(location.getLongitude()));
     
	/*         lat1 = location.getLatitude();
             lon1 = location.getLongitude();
            if(lat2 != 0 & lon2!=0)
	     	{float[] results =new float[1];
	     	Location.distanceBetween(lat1, lon1, lat2, lon2, results);
	     	 distance += String.valueOf(results[0]);
	         finalDistance1 = Double.parseDouble(distance);
	         finalDistance2 += finalDistance1;
	         tvDist.setText(String.valueOf(finalDistance2));}
           
	        lat2 = lat1;
	        lon2 = lon1;  */
		
		
		
		}
	
		}

	

/*	private void getdistance() {
        if((lat1 != lat2) & (lat2 != lon2 ))
        { float[] results = new float[1];
         Location.distanceBetween(lat1, lon1, lat2, lon2, results);
       
        }
       /// char[][] results = null;
		if(distance != null && String.valueOf(results[0]) != null)
        {  distance += String.valueOf(results[0]);
        finalDistance1 = Double.parseDouble(distance);
        finalDistance2 += finalDistance1;
        tvDist.setText(String.valueOf(finalDistance2));
        
        
        
        lat2 = lat1;
        lon2 = lon1;
        }
	}*/

	private void timerCount() {
		
		timeCount ++;                      // counter to get elapsed time. Only increments when location has changed.
		if (timeCount <60)                 // handling display of elapsed time when less than one minute
		{timer.setText(timeCount + "Seconds");}      //display seconds
		else if (timeCount > 59 && timeCount < 3600) // handling display of time when less one hour and more than one minute
		{ mins = timeCount/60;                       //calculate minutes
		seconds = timeCount%60;                      //calculate seconds
		timer.setText(mins +":" + seconds + "Minutes");}   //display  minutes:seconds    
		else if(timeCount > 3559)                               //handling display of time when more than one hour
		{hours = timeCount/3600;                                   //calculate hours
		mins = (
				timeCount%3600)/60;                                 //calculate minutes
		seconds = timeCount%60;                                      //calculate seconds
		timer.setText( hours + ":" + mins + ":"+ seconds + "Hours");   //display  hours:minutes:seconds
		}
	}

	private void addHeight() {//method to calculate accumulated altitude
		if(height - lastHeight  < 2  &&  height > lastHeight )//if height difference is less than 2 m . this is to avoid a large initial value where the lastHeight 
		{ heightDiff =     (float) (height - lastHeight  )  ;// has a zero initial value causing heightDiff to be equal to the initial altitude. It also has the effect 
			countHeight = countHeight + heightDiff;          //of smoothing out altitude spikes. && checks thaet new height is higher than last height
			tvAscent.setText((int) countHeight + " m");//  countHeight is cast to an int to eliminate figures to right of decimal point
			}
		lastHeight =  (float) height;
	}



	public void showHeight() {   //method to display current height
		tvAlt.setText(height + " m");
	}

	private void showMaxSp() {//method to get maximum speed. current speed is set to max speed if it is greater than current max speed
		if(currentSpeedKm > maxsp)
		{maxsp = currentSpeedKm;}
    	String mx = String.format( "%.1f", maxsp);
		tvMaxSp.setText(mx + " Km/h");
	
	}

	public void showSpeedKm() {
		// method to show current speed
		 currentSpeedKm = (float) (currentSpeed*3.6); //multiply by 3600 to convert to hours and divide by 1000 to get kilometers
		   //show speed in kilometers per hour to one place of decimals
		 speed = String.format("%.1f", currentSpeedKm);//from http://stackoverflow.com/questions/11072340/i-need-to-round-a-float-to-two-decimal-places-in-java
		txtvukm.setText(speed + " Km/h");
		
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
	}

}
