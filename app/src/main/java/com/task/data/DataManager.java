package com.task.data;

import com.task.data.db.LocalDatabase;
import com.task.data.remote.Repository;
import com.task.model.response.domain.WikiResult;

import io.reactivex.Single;

/***
 *
 * Repo pattern as person arch components.
 */


public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();
    private final Repository repository;
    private final LocalDatabase localDataBase;

    /***
     * Will be provided from dagger to avoid creating hard dependency.
     * @param repository
     * @param localDatabase
     */

    public DataManager(Repository repository, LocalDatabase localDatabase) {
        this.repository = repository;
        this.localDataBase = localDatabase;
    }


    /***
     * Method to maintain the calls , based on internet connectivity,either db or
     * repository will be caled.
     * @param query
     * @param isOnline
     * @return Single<WikiResult></WikiResult>
     */

    public Single<WikiResult> getWikiData(String query, boolean isOnline) {

        if (isOnline) {
            return repository.getWikiData(query);
        } else {
            return localDataBase.getWikiDataFromDb(query);
        }
    }
}
