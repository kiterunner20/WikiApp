package com.task.base;

public interface BaseView {

    void showToast(String message);

    void showToast(int resId);

    void showError(String errorMessage);

    void showContent();

    void showProgress();
}
