package com.example.mheshamg.xmovies.view.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.mheshamg.xmovies.R;


import com.example.mheshamg.xmovies.business.ShowsLocalCacheManager;

public class FavoriteDetailsActivity extends BaseMovieDetailsActivity {

    private FloatingActionButtonActionInterface floatingActionButtonActionInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFloatingActionButtonIcon(R.drawable.ic_delete);
        floatingActionButtonActionInterface = () -> {
            ShowsLocalCacheManager.getInstance(FavoriteDetailsActivity.this).deleteShow(show);
            Snackbar snackbar=  Snackbar.make(rootLayout,"Movie Deleted",Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(FavoriteDetailsActivity.this, R.color.colorPrimary));
            snackbar.show();
        };
        setFloatingActionButtonActionInterface(floatingActionButtonActionInterface);
    }
}
