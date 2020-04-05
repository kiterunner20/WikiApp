package com.task.ui.searchlist;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.task.App;
import com.task.R;
import com.task.base.BaseFragment;
import com.task.base.ViewModelFactory;
import com.task.model.response.domain.WikiResult;
import com.task.ui.searchlist.adapter.WikiListAdapter;
import com.task.util.NetworkManager;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class WikiSearchResultFragment extends BaseFragment implements WikiListAdapter.WikiResultItemSelectedListner {

    private static final String TAG = WikiSearchResultFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_error)
    TextView tvError;
    @Inject
    ViewModelFactory viewModelFactory;
    private WikiSearchResultViewModel viewModel;
    private WikiResult wikiResult;
    private boolean isLoading;
    private WikiListAdapter adapter;

    public static WikiSearchResultFragment newInstance() {
        Bundle args = new Bundle();
        WikiSearchResultFragment fragment = new WikiSearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_search_result;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    viewModel.getWikiData(query, NetworkManager.isNetworkAvailable(getContext()));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                if (newText.length() > 0) {
//                    viewModel.getWikiData(newText, NetworkManager.isNetworkAvailable(getContext()));
//                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initDataCalls() {
    }

    @Override
    protected void initObservers() {
        viewModel.getNewsMutableLiveData().observe(this, wikiViewState -> {
            isLoading = false;

            switch (wikiViewState.getCurrentState()) {

                case LOADING:
                    Log.d(TAG, "Loading");
                    break;

                case FAILED:
                    Log.e(TAG, "Failed " + wikiViewState.getError());
                    recyclerView.setVisibility(View.GONE);
                    tvError.setVisibility(View.GONE);
                    tvError.setText(wikiViewState.getError());
                    break;
                case SUCCESS:
                    recyclerView.setVisibility(View.VISIBLE);
                    tvError.setVisibility(View.GONE);

                    if (adapter != null && wikiViewState.getData() != null &&
                            wikiViewState.getData().isSuccess()) {
                        WikiSearchResultFragment.this.wikiResult = wikiViewState.getData();
                        if (wikiResult.dataList().size() > 0) {
                            adapter.addData(wikiResult.dataList());
                        }
                    }

                    break;
            }

        });
    }

    @Override
    protected void initViewModels() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WikiSearchResultViewModel.class);
    }


    @Override
    protected void initDependencies() {
        App.get(getContext()).getAppComponent().inject(this);
        adapter = new WikiListAdapter(this);
    }

    @Override
    protected void onReady(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(int selectedItemPosition) {

    }
}
