package com.example.mheshamg.xmovies.rest;

import com.example.mheshamg.xmovies.model.MoviesResponse;

import io.reactivex.Single;

import static com.example.mheshamg.xmovies.rest.Constants.API_KEY;

public class UpComingMoviesNetworkApiGetter extends BaseMoviesNetworkApiGetter {

    private static final String TAG= UpComingMoviesNetworkApiGetter.class.getSimpleName();

    @Override
    public void getMovies(String query)
    {
        Single<MoviesResponse> moviesResponseSingleObservable = apiService.getUpComingMovies(API_KEY);
        bindObserverToObservable(moviesResponseSingleObservable);
    }
}

