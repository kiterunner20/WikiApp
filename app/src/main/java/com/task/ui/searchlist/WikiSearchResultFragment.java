package com.task.ui.searchlist;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.task.App;
import com.task.R;
import com.task.base.BaseFragment;
import com.task.base.ViewModelFactory;
import com.task.model.response.domain.WikiResult;
import com.task.ui.WikiActivity;
import com.task.ui.searchlist.adapter.WikiListAdapter;
import com.task.ui.wikisourceview.WikiSourceWebViewFragment;
import com.task.util.NetworkManager;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnTextChanged;

public class WikiSearchResultFragment extends BaseFragment implements WikiListAdapter.WikiResultItemSelectedListner {

    private static final String TAG = WikiSearchResultFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView tvTitle;
    @BindView(R.id.edt_search_text)
    EditText etSearchText;
    @Inject
    ViewModelFactory viewModelFactory;
    private WikiSearchResultViewModel viewModel;
    private WikiResult wikiResult;
    private WikiListAdapter adapter;
    boolean intialCacheLoading;

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initDataCalls() {

    }

    @Override
    protected void initObservers() {
        viewModel.getNewsMutableLiveData().observe(this, wikiViewState -> {

            switch (wikiViewState.getCurrentState()) {

                case LOADING:
                    maintainUi(true, false, false);
                    break;

                case FAILED:

                    maintainUi(false, true, false);
                    tvError.setText(wikiViewState.getError());
                    break;
                case SUCCESS:

                    maintainUi(false, false, true);

                    if (adapter != null && wikiViewState.getData() != null &&
                            wikiViewState.getData().isSuccess()) {
                        WikiSearchResultFragment.this.wikiResult = wikiViewState.getData();
                        if (wikiResult.dataList().size() > 0) {
                            adapter.addData(wikiResult.dataList());
                        }
                    }

                    if (!intialCacheLoading) {
                        if (etSearchText.getText().toString().length() == 0) {
                            recyclerView.setVisibility(View.GONE);
                        }
                    }

                    break;
            }

        });
    }


    @Override
    protected void initViewModels() {
        if (null == viewModel) {
            viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(WikiSearchResultViewModel.class);
        }
    }


    @Override
    protected void initDependencies() {
        App.get(getContext()).getAppComponent().inject(this);
        adapter = new WikiListAdapter(this);
    }

    @Override
    protected void onReady(Bundle savedInstanceState) {
        tvTitle.setText("Wikipedia");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }


    @OnTextChanged(R.id.edt_search_text)
    void onTextChanged() {
        if (etSearchText.getText().toString().length() > 0) {
            String query = etSearchText.getText().toString();
            viewModel.getWikiData(query, NetworkManager.isNetworkAvailable(getContext()));
            intialCacheLoading = false;
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }


    @Override
    public void onItemSelected(int selectedItemPosition) {
        ((WikiActivity) getActivity()).replaceFragment(
                WikiSourceWebViewFragment.newInstance(adapter.getWikiData().get(selectedItemPosition).title()));
    }

    private void maintainUi(boolean isLoading, boolean isError, boolean isSuccess) {

        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            tvError.setVisibility(View.GONE);
        } else if (isError) {
            tvError.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        } else if (isSuccess) {

            recyclerView.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }

    }


}
