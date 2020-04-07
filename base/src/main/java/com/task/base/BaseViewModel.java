package com.task.base;


import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    private static final String TAG = BaseViewModel.class.getSimpleName();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected final void addCompositeDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }

}
