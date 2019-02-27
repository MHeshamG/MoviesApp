package com.example.mheshamg.xmovies.fagments;

import android.os.Bundle;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;
import com.example.mheshamg.xmovies.rest.BaseMoviesNetworkApiGetter;
import com.example.mheshamg.xmovies.rest.SearchMoviesNetworkApiGetter;


public class SearchFragment extends BaseFragment {

    private String query;

    public SearchFragment() {
        // Required empty public constructor
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        baseFragmentPresenter = new BaseFragmentPresenterClass();
        BaseMoviesNetworkApiGetter searchMoviesNetworkApiGetter = new BaseMoviesNetworkApiGetter();
        searchMoviesNetworkApiGetter.setQuery(query);
        baseFragmentPresenter.setMoviesGetter(searchMoviesNetworkApiGetter);
        baseFragmentPresenter.setView(this);
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(), R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData();
    }
}
