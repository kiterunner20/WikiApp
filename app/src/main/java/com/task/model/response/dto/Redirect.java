
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

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

}
