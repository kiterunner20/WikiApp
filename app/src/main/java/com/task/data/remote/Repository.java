package com.task.data.remote;

import com.task.data.db.LocalDatabase;
import com.task.model.mapper.WikiDataListMapper;
import com.task.model.response.domain.WikiResult;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private static final String TAG = Repository.class.getSimpleName();
    private final RemoteService remoteService;
    private final LocalDatabase localDatabase;


    public Repository(RemoteService remoteService, LocalDatabase localDatabase) {
        this.remoteService = remoteService;
        this.localDatabase = localDatabase;
    }


    public Single<WikiResult> getWikiData(String query) {
        return remoteService.getWikiData("query", "json", "pageimages|pageterms", "prefixsearch",
                1, 2, "thumbnail", 50, 10, "description",
                query, 10)
                .subscribeOn(Schedulers.io())
                .map(WikiDataListMapper.mapDtoDataToDomain())
                .map(wikiResult -> {
                    localDatabase.insertWikiResultsToDb(wikiResult.dataList());
                    return wikiResult;
                });
    }
}
