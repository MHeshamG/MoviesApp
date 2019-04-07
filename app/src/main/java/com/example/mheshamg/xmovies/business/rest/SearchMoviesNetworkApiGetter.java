package com.example.mheshamg.xmovies.business.rest;

import com.example.mheshamg.xmovies.model.ShowsResponse;

import io.reactivex.Single;

public class SearchMoviesNetworkApiGetter extends BaseMoviesNetworkApiGetter {

    private static final String TAG= SearchMoviesNetworkApiGetter.class.getSimpleName();

    @Override
    public void getMovies(String query) {
        Single<ShowsResponse> moviesResponseSingleObservable = apiService.searchForMovie(Constants.API_KEY,query);
        bindObserverToObservable(moviesResponseSingleObservable);
    }
}
