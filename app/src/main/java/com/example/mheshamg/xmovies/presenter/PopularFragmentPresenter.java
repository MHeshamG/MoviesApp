package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.PopularMoviesNetworkApiGetter;

import io.reactivex.observers.DisposableObserver;

public class PopularFragmentPresenter extends BaseFragmentPresenterClass{

    private final static String TAG=UpComingFragmentPresenter.class.getSimpleName();

    public PopularFragmentPresenter(MainActivityPresenter mainActivityPresenter)
    {
        super(mainActivityPresenter);
        moviesParser=new PopularMoviesNetworkApiGetter();
    }






}
