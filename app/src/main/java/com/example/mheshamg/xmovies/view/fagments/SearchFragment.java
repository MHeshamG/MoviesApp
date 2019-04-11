package com.example.mheshamg.xmovies.view.fagments;

import android.content.Intent;
import android.os.Bundle;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.view.activity.NetworkAPIMovieDetailsActivity;
import com.example.mheshamg.xmovies.view.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;
import com.example.mheshamg.xmovies.business.movie_getters.SearchMoviesNetworkApiGetter;


public class SearchFragment extends NetworkBasedFragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnMovieClickListener((int position)-> {
            Intent detailsActiviyIntent=new Intent(getContext(), NetworkAPIMovieDetailsActivity.class);
            detailsActiviyIntent.putExtra("Show",movies.get(position));
            startActivity(detailsActiviyIntent);
        });
        context=getContext();
        baseFragmentPresenter=new BaseFragmentPresenterClass();
        baseFragmentPresenter.setMoviesGetter(new SearchMoviesNetworkApiGetter());
        baseFragmentPresenter.setView(this);
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(), R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData(getQuery());
    }
}
