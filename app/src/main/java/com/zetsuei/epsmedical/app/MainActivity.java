package com.zetsuei.epsmedical.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class MainActivity extends AppCompatActivity {
    EPSWebViewFragment webViewFragment;
    private Tracker mTracker;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            webViewFragment = new EPSWebViewFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, webViewFragment)
                    .commit();
        }
        EPSApplication application = (EPSApplication) getApplication();
        mTracker = application.getDefaultTracker();

        mTracker.setScreenName("Main");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("BF95FAC47662E2AE0380508754BDCEEA")
                .build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_help:
                String url = "http://casahospitalaria.com/mibew/client.php?locale=es";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            case R.id.action_refresh:
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
                if (fragment instanceof EPSWebViewFragment) {
                    EPSWebViewFragment webViewFragment = (EPSWebViewFragment) fragment;
                    webViewFragment.getWebView().reload();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment instanceof EPSWebViewFragment) {
            EPSWebViewFragment webViewFragment = (EPSWebViewFragment) fragment;
            if (webViewFragment.canGoBack()) {
                webViewFragment.goBack();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
