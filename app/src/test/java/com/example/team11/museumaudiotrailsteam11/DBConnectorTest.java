package com.example.team11.museumaudiotrailsteam11;


import android.test.suitebuilder.annotation.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import android.support.test.InstrumentationRegistry;

/**
 * Created by c1674052 on 20/03/2017.
 */
@RunWith(JUnit4.class)
@LargeTest
public class DBConnectorTest {

    private DBConnector db;

    @Override
    public void setUp() throws Exception{
        DBConnector db = new DBConnector();

    }

}
