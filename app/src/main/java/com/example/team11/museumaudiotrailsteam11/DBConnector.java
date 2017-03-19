package com.example.team11.museumaudiotrailsteam11;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by c1630186 on 19/03/2017.
 */
public class DBConnector {
    DBSQLiteHelper openHelper;
    String TABLE_NAME = "ListExhibits";
    String COL_ID = "Exhibit_ID";
    String COl_Exhibit = "Exhibit_Txt";

    public DBConnector(Context ctx){
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

    public int removeItem(String id){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        String exhibitLocation = DBSQLiteHelper.COL_ID + " = ?";
        String[] args = {id};
        int deleted = db.delete(DBSQLiteHelper.TABLE_NAME, exhibitLocation, args);
        return deleted;
    }
    
}
