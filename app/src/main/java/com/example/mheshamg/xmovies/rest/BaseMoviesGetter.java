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

public abstract class BaseMoviesGetter {

    private final static String TAG=BaseMoviesGetter.class.getSimpleName();

    protected ApiInterface apiService;
    protected DisposableSingleObserver<MoviesResponse> moviesResponseDisposableSingleObserver;
    protected ArrayList<Movie> moviesList;
    protected PublishSubject<MoviesResponse> moviesResponsePublishSubject;

    public BaseMoviesGetter()
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

    public void bindObserverToObservable(Single<MoviesResponse> moviesResponseSingleObservable){
        moviesResponseSingleObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(moviesResponseDisposableSingleObserver);
    }
}
