package com.example.mheshamg.xmovies.business;

import com.example.mheshamg.xmovies.model.Show;

import java.util.List;

public interface MovieSubject {
    void registerObserver(MoviesObserver moviesObserver);
    void notifyObservers(List<Show> shows);
}
