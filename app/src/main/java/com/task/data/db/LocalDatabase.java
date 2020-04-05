package com.task.data.db;


public class LocalDatabase  {

    private static final String TAG = LocalDatabase.class.getSimpleName();
    private final AppDatabase appDataBase;

    public LocalDatabase(AppDatabase appDatabase) {
        this.appDataBase = appDatabase;
    }

}

