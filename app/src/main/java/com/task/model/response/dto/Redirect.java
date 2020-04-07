
package com.task.model.response.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Redirect {

    @SerializedName("index")
    @Expose
    private int index;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;

    public int getIndex() {
        return index;
    }


    public String getFrom() {
        return from;
    }


    public String getTo() {
        return to;
    }


}
