package com.example.joeymejias.googleplayservicespractice;

import android.app.Application;

import com.google.android.gms.analytics.Tracker;

/**
 * Created by joey on 8/9/16.
 */
public class AnalyticsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AnalyticsTrackers.initialize(this);
    }

    public static Tracker getDefaultTracker(){
        return AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);
    }
}
