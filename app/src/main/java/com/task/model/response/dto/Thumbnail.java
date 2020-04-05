
package com.task.model.response.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;

    public String getSource() {
        return source != null ? source : "";
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
