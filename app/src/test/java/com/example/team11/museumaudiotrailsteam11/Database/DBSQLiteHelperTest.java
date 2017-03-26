package com.example.team11.museumaudiotrailsteam11.Database;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by c1674052 on 21/03/2017.
 */
public class DBSQLiteHelperTest extends AndroidTestCase{

    private DBSQLiteHelper db;

    @Test
    public void testDropDB(){
        assertTrue(mContext.deleteDatabase(DBSQLiteHelper.DATABASE_NAME));
        System.out.println("testDropDb pass");

    }

    @Test
    public void testCreateDB(){
        DBSQLiteHelper dbHelper = new DBSQLiteHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        assertTrue(db.isOpen());
        db.close();
    }

    @Test
    public void testInsertData(){
//        DBSQLiteHelper dbHelper = new DBSQLiteHelper(mContext);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();

        assertNotNull(db);


    }
}