package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.MoviesObserver;
import com.example.mheshamg.xmovies.rest.TopRatedMoviesNetworkApiGetter;


public class TopRatedFragmentPresenter extends BaseFragmentPresenterClass {

    private final static String TAG=TopRatedFragmentPresenter.class.getSimpleName();

    public TopRatedFragmentPresenter(MainActivityPresenter mainActivityPresenter)
    {
        super(mainActivityPresenter);
        moviesParser=new TopRatedMoviesNetworkApiGetter();
    }
}
