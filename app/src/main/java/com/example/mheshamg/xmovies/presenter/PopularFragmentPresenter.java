package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.PopularMoviesGetter;

import io.reactivex.observers.DisposableObserver;

public class PopularFragmentPresenter extends BaseFragmentPresenterClass{

    private final static String TAG=UpComingFragmentPresenter.class.getSimpleName();

    private DisposableObserver<MoviesResponse> popularMoviesDisposableObserver;

    public PopularFragmentPresenter(MainActivityPresenter mainActivityPresenter)
    {
        super(mainActivityPresenter);
        moviesParser=new PopularMoviesGetter();
        popularMoviesDisposableObserver=createTopRatedMoviesDisposableSingleObserver();
        moviesParser.getMoviesResponsePublishSubject().subscribeWith(popularMoviesDisposableObserver);
    }






}
