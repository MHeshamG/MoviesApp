package com.example.mheshamg.xmovies;

import com.example.mheshamg.xmovies.model.Movie;

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
    public void notifyObservers(List<Movie> movies) {
        for(MoviesObserver moviesObserver:moviesObservers)
            moviesObserver.moviesRetrived(movies);
    }
}
