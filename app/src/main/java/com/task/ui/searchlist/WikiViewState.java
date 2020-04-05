package com.task.ui.searchlist;

import com.task.base.BaseViewState;
import com.task.model.response.domain.WikiResult;

public class WikiViewState  extends  BaseViewState<WikiResult>{

    public static final WikiViewState SUCCESS_STATE = new WikiViewState(null, State.SUCCESS, null);
    public static final WikiViewState LOADING_STATE = new WikiViewState(null, State.LOADING, null);
    public static final WikiViewState FAILED_STATE = new WikiViewState(null, State.FAILED, null);

    private WikiViewState(WikiResult data, State currentState, String error) {
        this.data = data;
        this.currentState = currentState;
        this.error = error;
    }

}
