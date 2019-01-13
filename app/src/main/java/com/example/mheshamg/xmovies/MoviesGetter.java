package com.example.mheshamg.xmovies;

import com.example.mheshamg.xmovies.model.Movie;

import java.util.List;

public interface MoviesGetter extends MovieSubject{
    void getMovies(String query);
}
