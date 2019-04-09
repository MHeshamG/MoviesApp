package com.example.mheshamg.xmovies.business;

import com.example.mheshamg.xmovies.model.Show;

import java.util.List;

public interface MoviesObserver {
    void moviesRetrived(List<Show> shows);
}
