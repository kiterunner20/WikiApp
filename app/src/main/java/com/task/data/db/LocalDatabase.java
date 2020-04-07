package com.task.data.db;


import com.task.data.db.entity.WikiDataEntity;
import com.task.model.mapper.WikiDataListMapper;
import com.task.model.response.domain.WikiResult;

import java.util.ArrayList;

import io.reactivex.Single;

public class LocalDatabase {

    private static final String TAG = LocalDatabase.class.getSimpleName();
    private final AppDatabase appDataBase;

    public LocalDatabase(AppDatabase appDatabase) {
        this.appDataBase = appDatabase;
    }


    /***
     * Insertion of search results from API to the DB.
     * @param dataList
     */

    public void insertWikiResultsToDb(ArrayList<WikiResult.DataList> dataList) {

        for (WikiResult.DataList wikiData : dataList) {
            appDataBase.wikiCacheDao().
                    upsert(new WikiDataEntity(wikiData.pageId(), wikiData.title(),
                            wikiData.imageUrl(), wikiData.width(), wikiData.height(), wikiData.description()));
        }
    }

    /***
     * Resultant data which is obtained from the db based on the query will be passed back
     * @param query
     * @return Single<WikiResult></>
     */

    public Single<WikiResult> getWikiDataFromDb(String query) {
        return appDataBase.wikiCacheDao().getWikiData(query)
                .map(WikiDataListMapper.mapDataEntityToDomain())
                .map(dataLists -> {
                    if (dataLists != null && dataLists.size() > 0) {
                        return WikiResult.create(true, dataLists.size(), (ArrayList<WikiResult.DataList>) dataLists, "");
                    } else {
                        return WikiResult.create(false, 0, null, "No data found in the local. Connect to internet!");
                    }
                });
    }
}

