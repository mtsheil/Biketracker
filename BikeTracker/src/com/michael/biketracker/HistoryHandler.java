package com.michael.biketracker;       //121 next   //hot or not

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryHandler { 
	public static final String KEY_ROWID =  "_id";
	public static final String KEY_DIST =  "distance";
	public static final String KEY_TIME =  "time";
	public static final String KEY_AVSP =  "av_speed";
	public static final String KEY_MAXSP =  "max_speed";
	public static final String KEY_CLIMB =  "meters_climb";
	public static final String KEY_CAL =  "calories";
	
	private static final String DATABASE_NAME =  "Cycle_History";
	private static final String DATABASE_TABLE =  "spins";
	private static final int DATABASE_VERSION =  1;
	
	private DbHelper myHelper;
	private final Context myContext;
	private SQLiteDatabase myDatabase;
	
	private static class DbHelper extends SQLiteOpenHelper{
		

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_DIST + " FLOAT NOT NULL, " +
					KEY_TIME + " INTEGER NOT NULL, " +
					KEY_AVSP + " FLOAT NOT NULL, " +
					KEY_MAXSP + " FLOAT NOT NULL, " +
					KEY_CLIMB + " INTEGER NOT NULL, " +
					KEY_CAL + " INTEGER NOT NULL);" 
				);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	public HistoryHandler(Context c){
		myContext = c;
	}
	
	public HistoryHandler open()throws SQLException{
		myHelper = new DbHelper(myContext);
		myDatabase = myHelper.getWritableDatabase();
		return this;
	}
	public void close() {
		myHelper.close();
	}

	public long createEntry( String dist, String time, String avspeed, String max,
			String mclimb, String cal) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_DIST, dist);
		cv.put(KEY_TIME, time);
		cv.put(KEY_AVSP, avspeed);
		cv.put(KEY_MAXSP, max);
		cv.put(KEY_CLIMB, mclimb);
		cv.put(KEY_CAL, cal);
		return myDatabase.insert(DATABASE_TABLE, null, cv);
		
	}
           //method to get data from db. 
	public String getData() {
	     String[] columns = new String[]{KEY_ROWID,KEY_DIST,KEY_TIME,KEY_AVSP,KEY_MAXSP,KEY_CLIMB,KEY_CAL};
	     Cursor cur = myDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);  //set up cursor to read from db table
	     String result = "";
	     int iRow = cur.getColumnIndex(KEY_ROWID);
	     int iDist = cur.getColumnIndex(KEY_DIST);
	     int iTime = cur.getColumnIndex(KEY_TIME);
	     int iAvsp = cur.getColumnIndex(KEY_AVSP);
	     int iMax = cur.getColumnIndex(KEY_MAXSP);
	     int iClimb = cur.getColumnIndex(KEY_CLIMB);
	     int iCal = cur.getColumnIndex(KEY_CAL);
	     //for loop to read through the data..start at first..as long as its not after last, move to next
	     for(cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()){
	    	 result = result + cur.getString(iRow) + "   " + cur.getString(iDist) + "   " + cur.getString(iTime) + "   " + cur.getString(iAvsp) + "   "
	     + cur.getString(iMax) + "   " + cur.getString(iClimb) + "   " + cur.getString(iCal) + "\n";
	     }
	     return result;
	}

	public String getDist(long l) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTime(long l) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAv(long l) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getClimb(long l) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCal(long l) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMax(long l) {
		// TODO Auto-generated method stub
		return null;
	}
}
