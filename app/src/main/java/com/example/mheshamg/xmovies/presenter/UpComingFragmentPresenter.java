package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.TopRatedMoviesParser;
import com.example.mheshamg.xmovies.rest.UpComingMoviesParser;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;

public class UpComingFragmentPresenter extends BaseFragmentPresenterClass {

    private final static String TAG=UpComingFragmentPresenter.class.getSimpleName();

    //Network
    private DisposableObserver<MoviesResponse> upComingMoviesDisposableObserver;

    public UpComingFragmentPresenter()
    {
        moviesParser=new UpComingMoviesParser();
        upComingMoviesDisposableObserver=createTopRatedMoviesDisposableSingleObserver();
        moviesParser.getMoviesResponsePublishSubject().subscribeWith(upComingMoviesDisposableObserver);
    }

}
