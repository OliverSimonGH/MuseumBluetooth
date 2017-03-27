package com.example.team11.museumaudiotrailsteam11;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.example.team11.museumaudiotrailsteam11.Database.DBSQLiteHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by c1674052 on 21/03/2017.
 */

@RunWith(AndroidJunit4.class)
public class DBSQLiteHelperTest extends AndroidTestCase{

    private DBSQLiteHelper db;

    @Before
    public void setUp() throws Exception{
        db = new DBSQLiteHelper(InstrumentationRegistry);
        db.open();

    }
    @After
    public void finsh(){
        db.close();
    }
    @Test
    public void testConditions() {
        assertNotNull(db);
    }

    @Test
    public void testDeleteAll(){
        int i = 0;
        db.removeHistoryValue(i++);

    }

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