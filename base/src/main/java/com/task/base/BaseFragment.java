package com.task.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.evernote.android.state.StateSaver;

import butterknife.ButterKnife;

/**
 * This class handles the lifecycle related things and act as a generic class,
 * the corresponding fragments can extend this class which reduces maintaining thing sepearately
 * for each fragment.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StateSaver.restoreInstanceState(this, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        int layoutId = getContentLayout();
        setHasOptionsMenu(true);
        return inflater.inflate(layoutId, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        StateSaver.saveInstanceState(this, outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDependencies();
        initViewModels();
        initObservers();
        initDataCalls();
        onReady(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract void initDataCalls();

    protected abstract void initObservers();

    protected abstract void initViewModels();

    public abstract @LayoutRes int getContentLayout();

    protected abstract void initDependencies();

    protected abstract void onReady(Bundle savedInstanceState);

}
