
package com.task.model.response.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("pages")
    @Expose
    private ArrayList<Page> pages = null;


    public ArrayList<Page> getPages() {
        return pages != null && pages.size() > 0 ? pages : new ArrayList<>() ;
    }


}
