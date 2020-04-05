package com.task.ui;


import android.os.Bundle;

import com.task.R;
import com.task.base.BaseActivity;
import com.task.ui.searchlist.WikiSearchResultFragment;


public class WikiActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void initDependencies() {

    }

    @Override
    public void onReady() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, WikiSearchResultFragment.newInstance())
                .commit();
    }

    @Override
    protected void initViewModel() {

    }


}
