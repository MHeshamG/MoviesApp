package com.example.mheshamg.xmovies.rest;

import android.util.Log;

import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;

import java.util.ArrayList;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseParser {

    private final static String TAG=BaseParser.class.getSimpleName();
    protected ApiInterface apiService;
    protected DisposableSingleObserver<MoviesResponse> moviesResponseDisposableSingleObserver;
    protected ArrayList<Movie> moviesList;
    protected PublishSubject<MoviesResponse> moviesResponsePublishSubject;

    public BaseParser()
    {
        moviesResponseDisposableSingleObserver=getMoviesResponseObserver();
        apiService=ApiClient.getApiInterface();
        moviesResponsePublishSubject= PublishSubject.create();
    }

    public abstract void fetchTopRatedMovies();

    public PublishSubject<MoviesResponse> getMoviesResponsePublishSubject() {
        return moviesResponsePublishSubject;
    }

    public DisposableSingleObserver<MoviesResponse> getMoviesResponseObserver()
    {
        return new DisposableSingleObserver<MoviesResponse>() {
            @Override
            public void onSuccess(MoviesResponse moviesResponse) {
                Log.i(TAG,moviesResponse.getResults().size()+"");
                moviesResponsePublishSubject.onNext(moviesResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }
        };
    }
}
