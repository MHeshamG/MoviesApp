package com.example.mheshamg.xmovies.rest;

import android.text.method.SingleLineTransformationMethod;

import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
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

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
