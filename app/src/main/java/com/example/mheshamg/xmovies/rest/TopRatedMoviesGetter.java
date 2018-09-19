package com.example.mheshamg.xmovies.rest;

import com.example.mheshamg.xmovies.model.MoviesResponse;

import io.reactivex.Single;

import static com.example.mheshamg.xmovies.rest.Constants.API_KEY;


public class TopRatedMoviesGetter extends BaseMoviesGetter {

    private static final String TAG= TopRatedMoviesGetter.class.getSimpleName();

    @Override
    public void fetchTopRatedMovies()
    {
        Single<MoviesResponse> moviesResponseSingleObservable = apiService.getTopRatedMovies(API_KEY);
        bindObserverToObservable(moviesResponseSingleObservable);
    }
}
