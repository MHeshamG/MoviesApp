package com.example.mheshamg.xmovies.rest;

import android.util.Log;

import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static com.example.mheshamg.xmovies.rest.Constants.API_KEY;

public class UpComingMoviesParser extends BaseParser {

    private static final String TAG= UpComingMoviesParser.class.getSimpleName();

    public UpComingMoviesParser()
    {

    }

    @Override
    public void fetchTopRatedMovies()
    {
        Single<MoviesResponse> moviesResponseSingleObservable = apiService.getUpComingMovies(API_KEY);
        moviesResponseSingleObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(moviesResponseDisposableSingleObserver);
    }
    @Override
    public DisposableSingleObserver<MoviesResponse> getMoviesResponseObserver() {
        Log.i(TAG,"getMoviesResponseObserver");
        return super.getMoviesResponseObserver();
    }
}

