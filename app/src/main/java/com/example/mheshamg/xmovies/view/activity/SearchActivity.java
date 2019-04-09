package com.example.mheshamg.xmovies.view.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.view.fagments.BaseFragment;
import com.example.mheshamg.xmovies.view.fagments.SearchFragment;

public class SearchActivity extends AppCompatActivity  {

    private Toolbar mToolbar;
    ActionBar actionBar;

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
            BaseFragment searchFragment = new SearchFragment();
            searchFragment.setQuery(query);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_containner,searchFragment).commit();
        }


    }

}
