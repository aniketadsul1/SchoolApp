package com.android.sudesi.schoolapp;

import android.app.Application;

import com.android.sudesi.schoolapp.dbconfig.DataBaseCon;

public class SchoolApp extends Application {

    public static DataBaseCon dbCon = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        try {
            if (dbCon != null) {
                dbCon.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
