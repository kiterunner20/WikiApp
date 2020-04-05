package com.task.data.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity public class WikiDataEntity {

    @PrimaryKey(autoGenerate = true) public long id;
}
