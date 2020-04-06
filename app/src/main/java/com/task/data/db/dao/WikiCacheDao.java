package com.task.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.task.data.db.entity.WikiDataEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class WikiCacheDao {

    @Transaction
    public boolean upsert(WikiDataEntity entity) {
        delete(entity.pageId);
        insert(entity);
        return true;
    }

    @Insert
    public abstract void insert(WikiDataEntity entity);

    @Query("DELETE FROM WikiDataEntity where page_id = :pageId")
    public abstract void delete(int pageId);

    @Query("SELECT * FROM WikiDataEntity WHERE title LIKE '%' || :title || '%'")
    public abstract Single<List<WikiDataEntity>> getWikiData(String title);

}
