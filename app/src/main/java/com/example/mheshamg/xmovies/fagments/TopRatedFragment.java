package com.example.mheshamg.xmovies.fagments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;
import com.example.mheshamg.xmovies.rest.TopRatedMoviesNetworkApiGetter;

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
        baseFragmentPresenter.setMoviesGetter(new TopRatedMoviesNetworkApiGetter());
        baseFragmentPresenter.setView(this);
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(),R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData();
    }
}