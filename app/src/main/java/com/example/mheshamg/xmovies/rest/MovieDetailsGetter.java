package com.example.mheshamg.xmovies.rest;

import android.util.Log;

import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MovieDetails;
import com.example.mheshamg.xmovies.model.MoviesResponse;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static com.example.mheshamg.xmovies.rest.Constants.API_KEY;

public class MovieDetailsGetter {

    private final static String TAG=BaseMoviesGetter.class.getSimpleName();


    protected ApiInterface apiService;
    protected DisposableSingleObserver<MovieDetails> movieDetailsDisposableSingleObserver;
    protected PublishSubject<MovieDetails> movieDetailsPublishSubject;
    private Single<MovieDetails> movieDetailsSingleObservable;

    public   MovieDetailsGetter()
    {
        movieDetailsDisposableSingleObserver=getMovieDetailsObserver();
        apiService=ApiClient.getApiInterface();
        movieDetailsPublishSubject= PublishSubject.create();
    }


    public void getMovieDetails(Long id){
        movieDetailsSingleObservable=apiService.getMovieDetails(id,API_KEY);
        bindObserverToObservable(movieDetailsSingleObservable);
    };

    public PublishSubject<MovieDetails> getMoviesResponsePublishSubject() {
        return movieDetailsPublishSubject;
    }

    public DisposableSingleObserver<MovieDetails> getMovieDetailsObserver()
    {
        return new DisposableSingleObserver<MovieDetails>() {
            @Override
            public void onSuccess(MovieDetails movieDetails) {
                movieDetailsPublishSubject.onNext(movieDetails);
                Log.i(TAG,movieDetails.getOriginalTitle());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }
        };
    }

    public void bindObserverToObservable(Single<MovieDetails> movieDetailsSingleObservable){
        movieDetailsSingleObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(movieDetailsDisposableSingleObserver);
    }
}
