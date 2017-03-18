package com.example.team11.museumaudiotrailsteam11;

import android.app.Notification;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.MessageFormat;

/**
 * Created by c1630186 on 12/03/2017.
 */
public class DBSQLiteHelper extends SQLiteOpenHelper{   // Extending the Android SQLDB class that offers all the SQL executable commands
    public static final String TABLE_NAME = "ListExhibits"; // First Table which is a List of Exhibits. Note this can change if needed.
    public static final String COL_ID = "Exhibit_ID";   // Name of First Column is Exhibit ID. Its a STRING as "Exhibit_ID" is a String
    public static final String COl_Item = "Exhibit_Txt";    // THe Column that'll contain the name of the Exhibit
    public static final int DATABASE_VERSION = 1;   // This is the first version of our DB
    public static final String DATABASE_NAME = "Exhibits.db";   // Name of the Database

    public DBSQLiteHelper(Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION);} // Constructor for the DB
    @Override
    public void onCreate(SQLiteDatabase db) {   // OnCreate Method - This is what the Db does when its first created
        String create = MessageFormat.format("CREATE TABLE IF NOT EXISTS {0}({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} VARCHAR);", TABLE_NAME, COL_ID, COl_Item); // Made a formatted string with some executable SQL Code
        db.execSQL(create); // Executes the SQL Code above.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
