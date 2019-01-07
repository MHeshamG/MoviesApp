package com.example.mheshamg.xmovies.rest;

import com.example.mheshamg.xmovies.model.MoviesResponse;

import io.reactivex.Single;

import static com.example.mheshamg.xmovies.rest.Constants.API_KEY;

public class PopularMoviesNetworkApiGetter extends BaseMoviesNetworkApiGetter {
    private static final String TAG= PopularMoviesNetworkApiGetter.class.getSimpleName();

    @Override
    public void getMovies()
    {
        Single<MoviesResponse> moviesResponseSingleObservable = apiService.getPopularMovies(API_KEY);
        bindObserverToObservable(moviesResponseSingleObservable);
    }
}
