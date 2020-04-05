package com.task.data;

import com.task.data.db.LocalDatabase;
import com.task.data.remote.Repository;
import com.task.model.response.domain.WikiResult;

import io.reactivex.Single;

public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();
    private final Repository repository;
    private final LocalDatabase localDataBase;

    public DataManager(Repository repository, LocalDatabase localDatabase) {
        this.repository = repository;
        this.localDataBase = localDatabase;
    }

    public Single<WikiResult>  getWikiData(String query, boolean isOnline) {

        if (isOnline) {
            return repository.getWikiData(query);
        }
        return null;
    }
}
