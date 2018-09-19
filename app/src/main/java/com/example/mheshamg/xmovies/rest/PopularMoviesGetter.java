package com.example.mheshamg.xmovies.rest;

import com.example.mheshamg.xmovies.model.MoviesResponse;

import io.reactivex.Single;

import static com.example.mheshamg.xmovies.rest.Constants.API_KEY;

public class PopularMoviesGetter extends BaseMoviesGetter {
    private static final String TAG= PopularMoviesGetter.class.getSimpleName();

    @Override
    public void fetchTopRatedMovies()
    {
        Single<MoviesResponse> moviesResponseSingleObservable = apiService.getPopularMovies(API_KEY);
        bindObserverToObservable(moviesResponseSingleObservable);
    }
}
