package com.example.mheshamg.xmovies.rest;

import android.util.Log;

import com.example.mheshamg.xmovies.BaseMoviesSubject;
import com.example.mheshamg.xmovies.MovieSubject;
import com.example.mheshamg.xmovies.MoviesObserver;
import com.example.mheshamg.xmovies.MoviesGetter;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseMoviesNetworkApiGetter implements MoviesGetter {

    private final static String TAG=BaseMoviesNetworkApiGetter.class.getSimpleName();

    protected ApiInterface apiService;
    protected DisposableSingleObserver<MoviesResponse> moviesResponseDisposableSingleObserver;
    private MovieSubject movieSubject;

    public BaseMoviesNetworkApiGetter()
    {
        moviesResponseDisposableSingleObserver=getMoviesResponseObserver();
        apiService=ApiClient.getApiInterface();
        movieSubject = new BaseMoviesSubject();
    }


    public DisposableSingleObserver<MoviesResponse> getMoviesResponseObserver()
    {
        return new DisposableSingleObserver<MoviesResponse>() {
            @Override
            public void onSuccess(MoviesResponse moviesResponse) {
                Log.d("hhh",""+moviesResponse.getResults().size());
                notifyObservers(moviesResponse.getResults());
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

    @Override
    public void registerObserver(MoviesObserver moviesObserver){
        movieSubject.registerObserver(moviesObserver);
    }

    @Override
    public void notifyObservers(List<Movie> movies){
        movieSubject.notifyObservers(movies);
    }
}
