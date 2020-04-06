package com.task.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.task.data.db.dao.WikiCacheDao;
import com.task.data.db.entity.WikiDataEntity;

@Database(entities = {WikiDataEntity.class}, version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WikiCacheDao wikiCacheDao();
}
