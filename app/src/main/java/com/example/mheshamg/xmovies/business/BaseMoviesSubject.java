package com.example.mheshamg.xmovies.business;

import com.example.mheshamg.xmovies.model.Show;

import java.util.ArrayList;
import java.util.List;

public class BaseMoviesSubject implements MovieSubject {
    private List<MoviesObserver> moviesObservers;

    public BaseMoviesSubject() {
        moviesObservers = new ArrayList<>();
    }

    @Override
    public void registerObserver(MoviesObserver moviesObserver) {
        if(moviesObserver!=null && !moviesObservers.contains(moviesObserver))
            moviesObservers.add(moviesObserver);
    }

    @Override
    public void notifyObservers(List<Show> shows) {
        for(MoviesObserver moviesObserver:moviesObservers)
            moviesObserver.moviesRetrived(shows);
    }
}
