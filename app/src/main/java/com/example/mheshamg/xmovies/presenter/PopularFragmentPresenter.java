package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.PopularMoviesParser;
import com.example.mheshamg.xmovies.rest.UpComingMoviesParser;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;

public class PopularFragmentPresenter extends BaseFragmentPresenterClass{

    private final static String TAG=UpComingFragmentPresenter.class.getSimpleName();

    //Network

    private DisposableObserver<MoviesResponse> popularMoviesDisposableObserver;

    public PopularFragmentPresenter()
    {
        moviesParser=new PopularMoviesParser();
        popularMoviesDisposableObserver=createTopRatedMoviesDisposableSingleObserver();
        moviesParser.getMoviesResponsePublishSubject().subscribeWith(popularMoviesDisposableObserver);
    }






}
