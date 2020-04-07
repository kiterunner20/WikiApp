package com.task.util;

import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public interface RxSingleSchedulers {


    /**
     * Act as generic to avoid code repeatation .
     * Do the multithreading with RxJava.
     */

    RxSingleSchedulers DEFAULT = new RxSingleSchedulers() {
        @Override
        public <T> SingleTransformer<T, T> applySchedulers() {
            return single -> single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    <T> SingleTransformer<T, T> applySchedulers();

}
