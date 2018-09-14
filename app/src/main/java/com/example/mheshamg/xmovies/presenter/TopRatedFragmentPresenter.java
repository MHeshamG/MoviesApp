package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.TopRatedMoviesParser;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;


public class TopRatedFragmentPresenter extends BaseFragmentPresenterClass {

    private final static String TAG=TopRatedFragmentPresenter.class.getSimpleName();

    //Network
    private DisposableObserver<MoviesResponse> topRatedMoviesDisposableObserver;

    public TopRatedFragmentPresenter()
    {
        moviesParser=new TopRatedMoviesParser();
        topRatedMoviesDisposableObserver=createTopRatedMoviesDisposableSingleObserver();
        moviesParser.getMoviesResponsePublishSubject().subscribeWith(topRatedMoviesDisposableObserver);
    }

}
