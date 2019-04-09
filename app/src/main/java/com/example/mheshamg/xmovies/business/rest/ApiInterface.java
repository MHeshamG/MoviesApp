package com.example.mheshamg.xmovies.business.rest;

import com.example.mheshamg.xmovies.model.ShowsResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Single<ShowsResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Single<ShowsResponse> getUpComingMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Single<ShowsResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("search/movie/")
    Single<ShowsResponse> searchForMovie(@Query("api_key") String apiKey , @Query("query") String query);
}
