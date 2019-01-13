package com.example.mheshamg.xmovies.rest;

import com.example.mheshamg.xmovies.model.MoviesResponse;

import io.reactivex.Single;

import static com.example.mheshamg.xmovies.rest.Constants.API_KEY;


public class TopRatedMoviesNetworkApiGetter extends BaseMoviesNetworkApiGetter {

    private static final String TAG= TopRatedMoviesNetworkApiGetter.class.getSimpleName();

    @Override
    public void getMovies(String query)
    {
        Single<MoviesResponse> moviesResponseSingleObservable = apiService.getTopRatedMovies(API_KEY);
        bindObserverToObservable(moviesResponseSingleObservable);
    }
}
