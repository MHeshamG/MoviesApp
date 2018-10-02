package com.example.mheshamg.xmovies.rest;

import com.example.mheshamg.xmovies.model.MoviesResponse;

import io.reactivex.Single;

import static com.example.mheshamg.xmovies.rest.Constants.API_KEY;

public class SearchMovieGetter extends BaseMoviesGetter {

    private static final String TAG= SearchMovieGetter.class.getSimpleName();
    private String query;

    public void setQuery(String query){
        this.query=query;
    }

    @Override
    public void fetchTopRatedMovies() {
        Single<MoviesResponse> moviesResponseSingleObservable = apiService.searchForMovie(API_KEY,query);
        bindObserverToObservable(moviesResponseSingleObservable);
    }
}
