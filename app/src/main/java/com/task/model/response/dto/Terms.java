
package com.task.model.response.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Terms {

    @SerializedName("description")
    @Expose
    private ArrayList<String> description = null;

    public ArrayList<String> getDescription() {
        return description != null && description.size() > 0 ? description : new ArrayList<>();
    }


}
