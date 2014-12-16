package com.michael.biketracker;       //117 next

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryHandler { 
	public static final String KEY_ROWID =  "_id";
	public static final String KEY_DIST =  "distance";
	public static final String KEY_TIME =  "time";
	public static final String KEY_MAXSP =  "max_speed";
	public static final String KEY_AVSP =  "av_speed";
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
					KEY_MAXSP + " FLOAT NOT NULL, " +
					KEY_AVSP + " FLOAT NOT NULL, " +
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
	
	public HistoryHandler open(){
		myHelper = new DbHelper(myContext);
		myDatabase = myHelper.getWritableDatabase();
		return this;
	}
	public void close() {
		myHelper.close();
	}

	public void createEntry( String avspeed, String time,
			String mclimb, String cal, String dist, String max) {
		// TODO Auto-generated method stub
		
	}
}
