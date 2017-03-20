package com.example.team11.museumaudiotrailsteam11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by c1630186 on 19/03/2017.
 */
public class DBConnector {
    DBSQLiteHelper openHelper;  // Instantiating a new SQLiteOpenHelper
    String TABLE_NAME = "ListExhibits"; // Calling the Table Name from the open helper
    String COL_ID = "Exhibit_ID";   // The Column id for the Exhibits table
    String COl_Exhibit = "Exhibit_Txt"; // The Column that stores the exhibit.
    String COL_ExhibitUUID = "beaconUUID";

    public DBConnector(Context ctx){    // Constructor for the DB Connector
        openHelper = new DBSQLiteHelper(ctx);   // Declaring a new SQLiteOpenHelper to use in the DB Connector
    }

    public long addExhibit(String exhibit){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Exhibit_Txt", exhibit);
        cv.put("beaconUUID", exhibit);
        long rowId = db.insert(DBSQLiteHelper.TABLE_NAME, null, cv);
        db.close();
        return rowId;
    }

    public int removeExhibit(String id){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        String exhibitLocation = DBSQLiteHelper.COL_ID + " = ?";
        String[] args = {id};
        int deleted = db.delete(DBSQLiteHelper.TABLE_NAME, exhibitLocation, args);
        return deleted;
    }

    public Map<Long, String> getExhibits(String queryterm){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        String[] exhibitColumns = {DBSQLiteHelper.COL_ID, DBSQLiteHelper.COL_Exhibit, DBSQLiteHelper.COL_ExhibitUUID};
        String location = null;
        String locationArguments[] = null;
        if (queryterm != null){
            location = DBSQLiteHelper.COL_Exhibit + " = ?";
            locationArguments = new String[]{queryterm};
        }
        Cursor myCursor = db.query(DBSQLiteHelper.TABLE_NAME, exhibitColumns, location, locationArguments, null, null, null);
        myCursor.moveToFirst();

        Map<Long, String> exhibits = new HashMap<>();
        if(myCursor.getCount() != 0){
            do{
                long id = myCursor.getLong(0);
                String exhibit = myCursor.getString(1);
                exhibits.put(id, exhibit);
            }while (myCursor.moveToNext());

        }
        return exhibits;
    }
}
