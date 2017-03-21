package com.example.team11.museumaudiotrailsteam11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.team11.museumaudiotrailsteam11.DBConnector;
import com.example.team11.museumaudiotrailsteam11.DBSQLiteHelper;
//import org.junit.Test;
import static junit.framework.Assert.assertNotNull;


/**
 * Created by c1674052 on 20/03/2017.
 */
public class DBConnectorAndroidTest extends AndroidTestCase{
    private static String mStudentName;
    private DBConnector db;

    public void testDropDB(){
        assertTrue(mContext.deleteDatabase(DBSQLiteHelper.DATABASE_NAME));
        System.out.println("testDropDB Pass");

    }
    public void testCreateDB(){
        DBSQLiteHelper dbHelper = new DBSQLiteHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        assertTrue(db.isOpen());
        db.close();
        System.out.println("testCreateDB Pass");
    }
    public void tesPreConditions(){
        assertNotNull(db);
    }
}
