package com.task.ui.searchlist;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.task.base.BaseViewModel;
import com.task.data.DataManager;
import com.task.model.response.domain.WikiResult;
import com.task.util.RxSingleSchedulers;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class WikiSearchResultViewModel extends BaseViewModel {

    private static final String TAG = WikiSearchResultViewModel.class.getSimpleName();
    private final DataManager dataManager;
    private final RxSingleSchedulers rxSingleSchedulers;
    MutableLiveData<WikiViewState> wikiMutableLiveData = new MutableLiveData<>();
    private Disposable disposable;

    @Inject
    public WikiSearchResultViewModel(DataManager dataManager, RxSingleSchedulers rxSingleSchedulers) {
        this.dataManager = dataManager;
        this.rxSingleSchedulers = rxSingleSchedulers;
    }

    public MutableLiveData<WikiViewState> getNewsMutableLiveData() {
        return wikiMutableLiveData;
    }

    public void getWikiData(String query, boolean isOnline) {

        onLoading();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = dataManager.getWikiData(query, isOnline)
                .compose(rxSingleSchedulers.applySchedulers())
                .subscribe(wikiResult -> {
                    if (wikiResult != null) {
                        if (wikiResult.isSuccess()) {
                            WikiSearchResultViewModel.this.onSuccess(wikiResult);
                        } else {
                            WikiSearchResultViewModel.this.onFailed(wikiResult);
                        }
                    } else {
                        WikiSearchResultViewModel.this.onError(new Throwable("Null Response From Server"));
                    }
                }, throwable -> {
                    onError(throwable);
                });

        addCompositeDisposable(disposable);
    }

    public void onSuccess(WikiResult wikiResult) {
        WikiViewState.SUCCESS_STATE.setData(wikiResult);
        wikiMutableLiveData.postValue(WikiViewState.SUCCESS_STATE);
    }

    public void onLoading() {
        wikiMutableLiveData.postValue(WikiViewState.LOADING_STATE);
    }

    public void onFailed(WikiResult wikiResult) {
        WikiViewState.FAILED_STATE.setError(wikiResult.errorMessage());
        wikiMutableLiveData.postValue(WikiViewState.FAILED_STATE);
    }

    public void onError(Throwable throwable) {
        Log.e(TAG, throwable.toString());
        WikiViewState.FAILED_STATE.setError("Unknown Error Occurred!Please check your internet connection or check the search query!");
        wikiMutableLiveData.postValue(WikiViewState.FAILED_STATE);
    }


}
