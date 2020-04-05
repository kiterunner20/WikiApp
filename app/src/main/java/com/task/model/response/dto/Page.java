
package com.task.model.response.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page {

    @SerializedName("pageid")
    @Expose
    private int pageid;
    @SerializedName("ns")
    @Expose
    private int ns;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("index")
    @Expose
    private int index;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("terms")
    @Expose
    private Terms terms;

    public int getPageid() {
        return pageid;
    }


    public int getNs() {
        return ns;
    }


    public String getTitle() {
        return title;
    }


    public int getIndex() {
        return index;
    }


    public Thumbnail getThumbnail() {
        return thumbnail != null ? thumbnail : null;
    }


    public Terms getTerms() {
        return terms != null ? terms : null;
    }


}
