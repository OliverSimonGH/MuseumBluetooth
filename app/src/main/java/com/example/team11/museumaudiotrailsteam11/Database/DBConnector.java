package com.example.team11.museumaudiotrailsteam11.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.team11.museumaudiotrailsteam11.Database.DBSQLiteHelper;

/**
 * Created by c1630186 on 19/03/2017.
 */
public class DBConnector {
    DBSQLiteHelper openHelper;  // Declaring a new SQLiteOpenHelper
    String TABLE_NAME = "ListExhibits"; // Calling the Table Name from the open helper
    String COL_ID = "Exhibit_ID";   // The Column id for the Exhibits table
    String COl_Exhibit = "Exhibit_Txt"; // The Column that stores the exhibit.

    public DBConnector(Context ctx){    // Constructor for the DB Connector
        openHelper = new DBSQLiteHelper(ctx);
    }

    public long addExhibit(String exhibit){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Exhibit_Txt", exhibit);
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

}
