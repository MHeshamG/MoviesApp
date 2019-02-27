package com.example.mheshamg.xmovies.fagments;

import android.os.Bundle;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;
import com.example.mheshamg.xmovies.rest.BaseMoviesNetworkApiGetter;

public class PopularFragment extends BaseFragment {

    public PopularFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        baseFragmentPresenter=new BaseFragmentPresenterClass();
        BaseMoviesNetworkApiGetter popularMoviesNetworkApiGetter = new BaseMoviesNetworkApiGetter();
        popularMoviesNetworkApiGetter.setQuery("popular");
        baseFragmentPresenter.setMoviesGetter(popularMoviesNetworkApiGetter);
        baseFragmentPresenter.setView(this);
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(), R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData();
    }
}
