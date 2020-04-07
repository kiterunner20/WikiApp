package com.task.ui.wikisourceview;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.task.R;
import com.task.base.BaseFragment;

import butterknife.BindView;


/**
 * Fragment for loading webview
 */

public class WikiSourceWebViewFragment extends BaseFragment {

    private static final String TITLE = "webview_title";
    private static final String BASE_URL = "https://en.wikipedia.org/wiki/";
    @BindView(R.id.web_view)
    WebView webView;

    public static WikiSourceWebViewFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        WikiSourceWebViewFragment fragment = new WikiSourceWebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initDataCalls() {

    }

    @Override
    protected void initObservers() {

    }

    @Override
    protected void initViewModels() {

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_news_source_webview;
    }

    @Override
    protected void initDependencies() {

    }


    @Override
    protected void onReady(Bundle savedInstanceState) {
        String title = null;
        String webUrl = null;

        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
        }

        webUrl = BASE_URL + title;

        if (webUrl != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(webUrl);
        }
    }
}
