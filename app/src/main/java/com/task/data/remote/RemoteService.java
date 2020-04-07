package com.task.data.remote;

import com.task.model.response.dto.WikiData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService {


    @GET("w/api.php?")
    Single<WikiData> getWikiData(@Query("action") String query, @Query("format") String format,
                                 @Query("prop") String prop, @Query("generator") String generator,
                                 @Query("redirects") int redirects, @Query("formatversion") int formatVersion,
                                 @Query("piprop") String piprop, @Query("pithumbsize") int pithumbsize,
                                 @Query("pilimit") int pilLimit, @Query("wbptterms") String wbPatternType,
                                 @Query("gpssearch") String searchQuery, @Query("gpslimit") int limit);
}
