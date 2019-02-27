package com.example.mheshamg.xmovies.fagments;

import android.os.Bundle;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;
import com.example.mheshamg.xmovies.rest.BaseMoviesNetworkApiGetter;

public class TopRatedFragment extends BaseFragment {

    private static final String TAG=TopRatedFragment.class.getSimpleName();


    public TopRatedFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        baseFragmentPresenter=new BaseFragmentPresenterClass();
        BaseMoviesNetworkApiGetter topRatedMoviesNetworkApiGetter = new BaseMoviesNetworkApiGetter();
        topRatedMoviesNetworkApiGetter.setQuery("TopRated");
        baseFragmentPresenter.setMoviesGetter(topRatedMoviesNetworkApiGetter);
        baseFragmentPresenter.setView(this);
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(),R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData();
    }
}