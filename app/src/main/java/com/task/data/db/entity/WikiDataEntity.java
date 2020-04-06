package com.task.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity public class WikiDataEntity {

    private static final String PAGE_ID = "page_id";
    private static final String TITLE = "title";
    private static final String IMAGE_URL = "image_url";
    private static final String DESCRIPTION = "description";


    @PrimaryKey(autoGenerate = true) public long id;

    @ColumnInfo(name = PAGE_ID) public int pageId;

    @ColumnInfo(name = TITLE) public String title;

    @ColumnInfo(name = IMAGE_URL) public String imageUrl;

    @ColumnInfo(name = DESCRIPTION) public String description;


    public WikiDataEntity(int pageId, String title, String imageUrl, String description){
        this.pageId = pageId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
    }

}
