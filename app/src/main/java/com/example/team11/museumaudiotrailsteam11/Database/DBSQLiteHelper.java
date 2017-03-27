package com.example.team11.museumaudiotrailsteam11.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.MessageFormat;

/**
 * Created by c1630186 on 12/03/2017.
 */
public class DBSQLiteHelper extends SQLiteOpenHelper{   // Extending the Android SQLDB class that offers all the SQL executable commands
    public static final int DATABASE_VERSION = 1;   // This is the first version of our DB
    public static final String DATABASE_NAME = "Exhibits.db";   // Name of the Database

    public static final String HISTORY_NAME = "History";
    public static final String HISTORY_ID = "History_ID";
    public static final String BEACON_UUID = "BeaconUUID";
    public static final String BEACON_MAJORNO = "MajorNo";
    public static final String BEACON_MINORNO = "MinorNo";
    public static final String BEACON_URL = "ExhibitURL";

    public DBSQLiteHelper(Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION);} // Constructor for the DB
    @Override
    public void onCreate(SQLiteDatabase db) {
        // OnCreate Method - This is what the Db does when its first created
        String history = MessageFormat.format("CREATE TABLE IF NOT EXISTS {0}({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} VARCHAR, {3} INTEGER, {4} INTEGER, {5} TEXT)", HISTORY_NAME, HISTORY_ID, BEACON_UUID, BEACON_MAJORNO, BEACON_MINORNO, BEACON_URL);
        db.execSQL(history);
    }

    public void createTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HISTORY_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public Cursor getAllHistoryContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + HISTORY_NAME, null);
        return data;
    }

    public void removeHistoryValue(int id){
        System.out.println(countRows());
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteHistoryVal = "DELETE FROM `" + HISTORY_NAME + "` WHERE `" + HISTORY_ID + "` = '" + id + "'";
        db.execSQL(deleteHistoryVal);
        System.out.println(countRows());
    }

    public Cursor GetHistoryURL(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String getHistoryURL = "SELECT " + BEACON_URL + " FROM `" + HISTORY_NAME + "` WHERE `" + HISTORY_ID + "` = '" + id + "'";
        Cursor data = db.rawQuery(getHistoryURL, null);
        return data;    }

    public boolean insertHistoryData(String beaconUUID, int beaconMajorNo, int beaconMinorNo, String beaconURL){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BEACON_UUID, beaconUUID);
        contentValues.put(BEACON_MAJORNO, beaconMajorNo);
        contentValues.put(BEACON_MINORNO, beaconMinorNo);
        contentValues.put(BEACON_URL, beaconURL);
        long result = db.insert(HISTORY_NAME, null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Cursor getHistoryItemID(String beaconUUID, String beaconMajorNo, String beaconMinorNo){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM `" + HISTORY_NAME +"` WHERE `" + BEACON_UUID + "` = '" + beaconUUID + "' AND `" + BEACON_MAJORNO + "` = '" + beaconMajorNo + "' AND `" + BEACON_MINORNO + "` = '" + beaconMinorNo + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

//    http://stackoverflow.com/questions/5202269/sqlite-query-in-android-to-count-rows
    public int countRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCount = db.rawQuery("select count(*) from " + HISTORY_NAME , null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }
}
