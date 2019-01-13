package com.example.mheshamg.xmovies.rest;

import com.example.mheshamg.xmovies.model.MoviesResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Single<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Single<MoviesResponse> getUpComingMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Single<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("search/movie/")
    Single<MoviesResponse> searchForMovie(@Query("api_key") String apiKey , @Query("query") String query);
}
