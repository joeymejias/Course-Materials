package com.joeymejias.hoppr;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    ImageView mSplashImage;
    ProgressBar mSplashProgress;
    RelativeLayout mSplashLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSplashLayout = (RelativeLayout) findViewById(R.id.splash_layout);
        mSplashImage = (ImageView) findViewById(R.id.splash_logo);
        mSplashImage.setVisibility(View.VISIBLE);

        // on some click or some loading we need to wait for...
        mSplashProgress = (ProgressBar) findViewById(R.id.splash_progress_bar);
        mSplashProgress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            onResume();
        }else {
            noNetworkAvailable();
        }
    }

    private void noNetworkAvailable() {
        Intent intent = new Intent(this, NoInternetActivity.class);
        startActivity(intent);
    }
}
