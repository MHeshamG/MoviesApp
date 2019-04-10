package com.example.mheshamg.xmovies.business.movie_getters;

import com.example.mheshamg.xmovies.frameworks.rest.BaseMoviesNetworkApiGetter;
import com.example.mheshamg.xmovies.model.ShowsResponse;

import io.reactivex.Single;

import static com.example.mheshamg.xmovies.frameworks.rest.Constants.API_KEY;

public class PopularMoviesNetworkApiGetter extends BaseMoviesNetworkApiGetter {
    private static final String TAG= PopularMoviesNetworkApiGetter.class.getSimpleName();

    @Override
    public void getMovies(String query)
    {
        Single<ShowsResponse> moviesResponseSingleObservable = apiService.getPopularMovies(API_KEY);
        bindObserverToObservable(moviesResponseSingleObservable);
    }
}
