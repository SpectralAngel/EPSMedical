package com.zetsuei.epsmedical.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A placeholder fragment containing a simple view.
 */
public class EPSWebViewFragment extends Fragment {

    @InjectView(R.id.webView)
    WebView webView;

    public EPSWebViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.web_fragment, container, false);
        ButterKnife.inject(this, rootView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new EPSWebViewClient());
        webView.loadUrl("http://hospinet.epsmedica.com");
        CookieManager.getInstance().setAcceptCookie(true);
        return rootView;
    }

    public boolean canGoBack() {
        return webView.canGoBack();
    }

    public void goBack() {
        if(webView.canGoBack()) {
            webView.goBack();
        }
    }
}
