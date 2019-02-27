package com.example.mheshamg.xmovies.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.example.mheshamg.xmovies.R;

import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.fagments.SearchFragment;


public class SearchActivity extends AppCompatActivity{

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
            SearchFragment searchFragment = new SearchFragment();
            searchFragment.setQuery(query);

            FragmentManager fragmentManager = getSupportFragmentManager ();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
            fragmentTransaction.add(R.id.fragment_containner,searchFragment);
            fragmentTransaction.commit ();
        }
    }
}
