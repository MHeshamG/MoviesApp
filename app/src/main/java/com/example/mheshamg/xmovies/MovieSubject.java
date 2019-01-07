package com.example.mheshamg.xmovies;

import com.example.mheshamg.xmovies.model.Movie;

import java.util.List;

public interface MovieSubject {
    void registerObserver(MoviesObserver moviesObserver);
    void notifyObservers(List<Movie> movies);
}
