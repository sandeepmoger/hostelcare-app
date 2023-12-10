package com.sandeep.hostelcare;

import android.app.Application;

import com.onesignal.Continue;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);
        OneSignal.initWithContext(this, "bd062913-293e-47bf-8038-084b97ccc169");
        OneSignal.getNotifications().requestPermission(true, Continue.none());
    }
}
