package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.TopRatedMoviesGetter;

import io.reactivex.observers.DisposableObserver;


public class TopRatedFragmentPresenter extends BaseFragmentPresenterClass {

    private final static String TAG=TopRatedFragmentPresenter.class.getSimpleName();

    //Network
    private DisposableObserver<MoviesResponse> topRatedMoviesDisposableObserver;

    public TopRatedFragmentPresenter(MainActivityPresenter mainActivityPresenter)
    {
        super(mainActivityPresenter);
        moviesParser=new TopRatedMoviesGetter();
        topRatedMoviesDisposableObserver=createTopRatedMoviesDisposableSingleObserver();
        moviesParser.getMoviesResponsePublishSubject().subscribeWith(topRatedMoviesDisposableObserver);
    }

}
