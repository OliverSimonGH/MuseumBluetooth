package com.example.team11.museumaudiotrailsteam11;

import android.app.Notification;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.MessageFormat;

/**
 * Created by c1630186 on 12/03/2017.
 */
public class DBSQLiteHelper extends SQLiteOpenHelper{
    public static final String TABLE_NAME = "ListExhibits";
    public static final String COL_ID = "Exhibit_ID";
    public static final String COl_Item = "Exhibit_Txt";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Exhibits.db";

    public DBSQLiteHelper(Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = MessageFormat.format("CREATE TABLE IF NOT EXISTS {0}({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} VARCHAR);");
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
