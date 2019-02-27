package com.example.mheshamg.xmovies;

import com.example.mheshamg.xmovies.model.Show;

import java.util.List;

public interface MoviesObserver {
    void moviesRetrived(List<Show> shows);
}
