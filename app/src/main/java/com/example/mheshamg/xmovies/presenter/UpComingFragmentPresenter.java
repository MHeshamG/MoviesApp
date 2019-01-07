package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.UpComingMoviesNetworkApiGetter;

import io.reactivex.observers.DisposableObserver;

public class UpComingFragmentPresenter extends BaseFragmentPresenterClass {

    private final static String TAG=UpComingFragmentPresenter.class.getSimpleName();

    public UpComingFragmentPresenter(MainActivityPresenter mainActivityPresenter)
    {
        super(mainActivityPresenter);
        moviesParser=new UpComingMoviesNetworkApiGetter();
    }

}
