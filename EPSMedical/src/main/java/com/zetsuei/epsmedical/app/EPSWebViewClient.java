package com.zetsuei.epsmedical.app;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Carlos on 4/12/2014.
 */
public class EPSWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().equals("hospinet.epsmedical.com")) {
            // This is my web site, so do not override; let my WebView load the page
            return false;
        }
        return true;
    }
}
