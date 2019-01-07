package com.example.mheshamg.xmovies;

import com.example.mheshamg.xmovies.model.Movie;

import java.util.List;

public interface MoviesObserver {
    void moviesRetrived(List<Movie> movies);
}
