package com.example.mheshamg.xmovies.rest;

import com.example.mheshamg.xmovies.model.MovieDetails;
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

    @GET("movie/{id}")
    Single<MovieDetails> getMovieDetails(@Path("id") long id, @Query("api_key") String apiKey);
    //search/movie?api_key=134da0f4a51a36498831652090ad9aaf&query=hello
}
