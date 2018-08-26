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


public class TopRatedMoviesParser {

    private static final String TAG= TopRatedMoviesParser.class.getSimpleName();
    private ApiInterface apiService;
    private DisposableSingleObserver<MoviesResponse> moviesResponseDisposableSingleObserver;
    private ArrayList<Movie> moviesList;

    private PublishSubject<MoviesResponse> moviesResponsePublishSubject;

    public TopRatedMoviesParser()
    {
        moviesResponseDisposableSingleObserver=getMoviesResponseObserver();
        apiService=ApiClient.getApiInterface();
        moviesResponsePublishSubject=PublishSubject.create();
    }




    public void fetchTopRatedMovies()
    {
        Single<MoviesResponse> moviesResponseSingleObservable = apiService.getTopRatedMovies(API_KEY);
        moviesResponseSingleObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(moviesResponseDisposableSingleObserver);
    }

    public PublishSubject<MoviesResponse> getMoviesResponsePublishSubject() {
        return moviesResponsePublishSubject;
    }

    private DisposableSingleObserver<MoviesResponse> getMoviesResponseObserver()
    {
        return new DisposableSingleObserver<MoviesResponse>() {
            @Override
            public void onSuccess(MoviesResponse moviesResponse) {
                moviesResponsePublishSubject.onNext(moviesResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }
        };
    }
}
