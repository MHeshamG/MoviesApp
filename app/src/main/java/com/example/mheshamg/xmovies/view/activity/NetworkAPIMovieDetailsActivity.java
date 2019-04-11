package com.example.mheshamg.xmovies.view.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.mheshamg.xmovies.R;


import com.example.mheshamg.xmovies.business.ShowsLocalCacheManager;

public class NetworkAPIMovieDetailsActivity extends BaseMovieDetailsActivity {

    private BaseMovieDetailsActivity.FloatingActionButtonActionInterface floatingActionButtonActionInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFloatingActionButtonIcon(R.drawable.ic_favorite);
        floatingActionButtonActionInterface = () -> {
            ShowsLocalCacheManager.getInstance(NetworkAPIMovieDetailsActivity.this).addShow(show);
            Snackbar snackbar = Snackbar.make(rootLayout, "Added To Favourites", Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(NetworkAPIMovieDetailsActivity.this, R.color.blue));
            snackbar.show();
        };
        setFloatingActionButtonActionInterface(floatingActionButtonActionInterface);
    }
}
