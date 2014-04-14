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
    Boolean isWebViewAvailable;

    public EPSWebViewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (webView != null) {
            webView.destroy();
        }
        View rootView = inflater.inflate(R.layout.web_fragment, container, false);
        ButterKnife.inject(this, rootView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new EPSWebViewClient());
        webView.loadUrl("http://hospinet.epsmedica.com");
        CookieManager.getInstance().setAcceptCookie(true);
        return rootView;
    }

    /**
     * Called when the fragment is visible to the user and actively running. Resumes the WebView.
     */
    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    /**
     * Called when the fragment is no longer resumed. Pauses the WebView.
     */
    @Override
    public void onResume() {
        webView.onResume();
        super.onResume();
    }

    /**
     * Called when the WebView has been detached from the fragment.
     * The WebView is no longer available after this time.
     */
    @Override
    public void onDestroyView() {
        isWebViewAvailable = false;
        super.onDestroyView();
    }

    /**
     * Called when the fragment is no longer in use. Destroys the internal state of the WebView.
     */
    @Override
    public void onDestroy() {
        if (webView != null) {
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

    public boolean canGoBack() {
        return webView.canGoBack();
    }

    public void goBack() {
        if(webView.canGoBack()) {
            webView.goBack();
        }
    }

    public WebView getWebView() {
        return webView;
    }
}
