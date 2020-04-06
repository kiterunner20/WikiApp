package com.task.model.mapper;

import com.task.data.db.entity.WikiDataEntity;
import com.task.model.response.domain.WikiResult;
import com.task.model.response.dto.Page;
import com.task.model.response.dto.WikiData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;


public class WikiDataListMapper {

    private static final String TAG = WikiDataListMapper.class.getSimpleName();
    public static final String NO_DATA_FOUND = "No data found";

    public static Function<WikiData, WikiResult> mapDtoDataToDomain() {

        return wikiData -> {

            WikiResult wikiResult = null;

            ArrayList<WikiResult.DataList> dataList = new ArrayList<>();

            if (wikiData != null) {
                if (wikiData.getQuery() != null) {
                    if (wikiData.getQuery().getPages() != null & wikiData.getQuery().getPages().size() > 0) {

                        for (Page page : wikiData.getQuery().getPages()) {

                            String thumbnail = "";
                            StringBuffer description = new StringBuffer();

                            if (page.getThumbnail() != null) {
                                if (page.getThumbnail().getSource() != null) {
                                    thumbnail = page.getThumbnail().getSource();
                                }
                            }

                            if (page.getTerms() != null) {
                                if (page.getTerms().getDescription() != null &&
                                        page.getTerms().getDescription().size() > 0) {
                                    for (String data : page.getTerms().getDescription()) {
                                        if (data != null) {
                                            description.append(data);
                                            description.append(" . ");
                                        }
                                    }
                                }
                            }

                            dataList.add(WikiResult.DataList.create(page.getPageid(), page.getTitle(),
                                    thumbnail, description.toString()));

                        }

                        wikiResult = WikiResult.create(true, dataList.size(), dataList, "");

                    } else {
                        wikiResult = WikiResult.create(false, 0, null, NO_DATA_FOUND);
                    }

                } else {
                    wikiResult = WikiResult.create(false, 0, null, NO_DATA_FOUND);
                }
            } else {
                wikiResult = WikiResult.create(false, 0, null, NO_DATA_FOUND);
            }

            return wikiResult;
        };

    }


    public static Function<List<WikiDataEntity>, List<WikiResult.DataList>> mapDataEntityToDomain() {
        return wikiDataEntities -> {

            List<WikiResult.DataList> dataLists = new ArrayList<>();

            for (WikiDataEntity wikiDataEntity : wikiDataEntities) {
                dataLists.add(WikiResult.DataList.create(wikiDataEntity.pageId, wikiDataEntity.title,
                        wikiDataEntity.imageUrl, wikiDataEntity.description));
            }

            return dataLists;
        };
    }
}
