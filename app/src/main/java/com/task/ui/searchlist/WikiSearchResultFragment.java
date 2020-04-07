package com.task.ui.searchlist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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


import com.evernote.android.state.State;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.task.App;
import com.task.R;
import com.task.base.BaseFragment;
import com.task.base.ViewModelFactory;
import com.task.model.response.domain.WikiResult;
import com.task.ui.WikiActivity;
import com.task.ui.searchlist.adapter.WikiListAdapter;
import com.task.ui.wikisourceview.WikiSourceWebViewFragment;
import com.task.util.NetworkManager;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnTextChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;

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
    @State
    boolean isNoInput = false;
    WikiResult wikiResult;
    private WikiListAdapter adapter;
    @State
    String query;

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
    protected void initDataCalls() {

    }

    /**
     * Observes to livedata , changes made in UI according
     */

    @Override
    protected void initObservers() {
        viewModel.getNewsMutableLiveData().observe(this, wikiViewState -> {

            switch (wikiViewState.getCurrentState()) {

                case LOADING:
                    maintainUi(true, false, false);
                    break;

                case FAILED:

                    maintainUi(false, true, false);
                    if (isNoInput) {
                        tvError.setVisibility(View.GONE);
                    } else {
                        tvError.setText(wikiViewState.getError());
                    }
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

                    break;
            }

        });
    }

    /**
     * Viewmodel intiallization . In the factory it will check for the viewmodel instance.
     * On config changes it will supply the same viewmodel.So that viewmodel won't be
     * created every time
     */

    @Override
    protected void initViewModels() {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(WikiSearchResultViewModel.class);
    }


    /**
     * Intialize all the dependencies.
     */

    @Override
    protected void initDependencies() {
        App.get(getContext()).getAppComponent().inject(this);
        adapter = new WikiListAdapter(this);
    }

    @Override
    protected void onReady(Bundle savedInstanceState) {
        tvTitle.setText(getString(R.string.title));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


    }


    /**
     *Listner to text changes, debounce operator used to manage the calls based on the time
     *Avoids unneccesory calls for every text change.
     */
    @SuppressLint("CheckResult")
    @OnTextChanged(R.id.edt_search_text)
    void onTextChanged() {
        if (etSearchText.getText().toString().length() > 0 && !etSearchText.getText().toString().equals(query)) {
            isNoInput = false;
            RxTextView.textChanges(etSearchText)
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(string -> {
                        query = etSearchText.getText().toString();
                        viewModel.getWikiData(query, NetworkManager.isNetworkAvailable(getContext()));
                    }, error -> {
                        Log.e("ERROR", "ERROR LISTENING: " + error.getMessage());
                    });

        } else if (etSearchText.getText().toString().length() == 0) {
            isNoInput = true;
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
