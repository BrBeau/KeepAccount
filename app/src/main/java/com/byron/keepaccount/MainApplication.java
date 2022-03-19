package com.byron.keepaccount;

import android.app.Application;

import com.byron.keepaccount.db.DbManager;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbManager.initDb(getApplicationContext());
    }
}
