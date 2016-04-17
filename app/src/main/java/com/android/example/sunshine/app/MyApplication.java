package com.android.example.sunshine.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Pranjal Paliwal on 4/8/2016.
*/
public class MyApplication extends Application {
	private static MyApplication sInstance;
	@Override
	public void onCreate(){
		super.onCreate();
		sInstance = this;
	}

	public static MyApplication getInstance(){
		return sInstance;
	}
	public static Context getAppContext(){
		return sInstance.getApplicationContext();
	}
}
