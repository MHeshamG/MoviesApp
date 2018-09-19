package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.UpComingMoviesGetter;

import io.reactivex.observers.DisposableObserver;

public class UpComingFragmentPresenter extends BaseFragmentPresenterClass {

    private final static String TAG=UpComingFragmentPresenter.class.getSimpleName();

    //Network
    private DisposableObserver<MoviesResponse> upComingMoviesDisposableObserver;

    public UpComingFragmentPresenter(MainActivityPresenter mainActivityPresenter)
    {
        super(mainActivityPresenter);
        moviesParser=new UpComingMoviesGetter();
        upComingMoviesDisposableObserver=createTopRatedMoviesDisposableSingleObserver();
        moviesParser.getMoviesResponsePublishSubject().subscribeWith(upComingMoviesDisposableObserver);
    }

}
