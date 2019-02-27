package com.example.mheshamg.xmovies.rest;

import com.example.mheshamg.xmovies.model.MoviesResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/{Category}")
    Single<MoviesResponse> getCatogerizedMovies(@Path("Category") String query , @Query("api_key") String apiKey);

    @GET("search/movie/")
    Single<MoviesResponse> searchForMovie(@Query("api_key") String apiKey , @Query("query") String query);
}
