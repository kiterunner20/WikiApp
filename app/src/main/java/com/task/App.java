package com.task;

import android.app.Application;
import android.content.Context;

import com.task.di.AppComponent;
import com.task.di.DaggerAppComponent;


public class App extends Application {

    public AppComponent appComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().application(this).build();

        appComponent.inject(this);

    }
}
