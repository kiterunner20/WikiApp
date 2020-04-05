
package com.task.model.response.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WikiData {

    @SerializedName("query")
    @Expose
    private Query query;


    public Query getQuery() {
        return query != null ? query : null;
    }


}
