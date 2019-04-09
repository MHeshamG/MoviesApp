package com.example.mheshamg.xmovies.business.rest;

import com.example.mheshamg.xmovies.model.ShowsResponse;

import io.reactivex.Single;

import static com.example.mheshamg.xmovies.business.rest.Constants.API_KEY;

public class UpComingMoviesNetworkApiGetter extends BaseMoviesNetworkApiGetter {

    private static final String TAG= UpComingMoviesNetworkApiGetter.class.getSimpleName();

    @Override
    public void getMovies(String query)
    {
        Single<ShowsResponse> moviesResponseSingleObservable = apiService.getUpComingMovies(API_KEY);
        bindObserverToObservable(moviesResponseSingleObservable);
    }
}

