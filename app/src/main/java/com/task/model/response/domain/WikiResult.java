package com.task.model.response.domain;

import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

@AutoValue
public abstract class WikiResult implements Parcelable {

    public static WikiResult create(boolean isSuccess, long totalItems, ArrayList<DataList> dataLists,
                                    String errorMessage) {
        return new AutoValue_WikiResult(isSuccess, totalItems, dataLists, errorMessage);
    }

    public abstract boolean isSuccess();

    public abstract long totalItems();

    @Nullable
    public abstract ArrayList<DataList> dataList();

    @Nullable
    public abstract String errorMessage();


    @AutoValue
    public abstract static class DataList implements Parcelable {

        public static DataList create(int pageId, String title, String imageUrl,
                                      String description) {
            return new AutoValue_WikiResult_DataList(pageId, title, imageUrl, description);
        }

        public abstract int pageId();

        @Nullable
        public abstract String title();

        @Nullable
        public abstract String imageUrl();

        @Nullable
        public abstract String description();

    }

}
