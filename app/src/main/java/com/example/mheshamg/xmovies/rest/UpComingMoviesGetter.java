package com.example.mheshamg.xmovies.rest;

import com.example.mheshamg.xmovies.model.MoviesResponse;

import io.reactivex.Single;

import static com.example.mheshamg.xmovies.rest.Constants.API_KEY;

public class UpComingMoviesGetter extends BaseMoviesGetter {

    private static final String TAG= UpComingMoviesGetter.class.getSimpleName();

    @Override
    public void fetchTopRatedMovies()
    {
        Single<MoviesResponse> moviesResponseSingleObservable = apiService.getUpComingMovies(API_KEY);
        bindObserverToObservable(moviesResponseSingleObservable);
    }
}

