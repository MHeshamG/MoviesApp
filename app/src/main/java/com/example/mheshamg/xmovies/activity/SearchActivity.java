package com.example.mheshamg.xmovies.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.presenter.MovieDetailsActivityPresenter;
import com.example.mheshamg.xmovies.presenter.SearchActivityPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

public class SearchActivity extends AppCompatActivity  implements SearchActivityPresenter.SearchViewInterface{

    private Toolbar mToolbar;
    ActionBar actionBar;
    private SearchActivityPresenter searchActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        if (intent!=null){
            String query=intent.getStringExtra(SearchManager.QUERY);
            searchActivityPresenter=new SearchActivityPresenter(this);
            searchActivityPresenter.retriveData(query);
        }
    }

    @Override
    public void updateView() {

    }
}
