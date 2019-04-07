package com.example.mheshamg.xmovies.business.room;

import android.content.Context;

import com.example.mheshamg.xmovies.business.BaseMoviesSubject;
import com.example.mheshamg.xmovies.business.MovieSubject;
import com.example.mheshamg.xmovies.business.MoviesGetter;
import com.example.mheshamg.xmovies.business.MoviesObserver;
import com.example.mheshamg.xmovies.model.Show;

import java.util.List;

public class DatabaseMoviesGetter implements MoviesGetter {

    private MovieSubject movieSubject;
    private Context context;

    public DatabaseMoviesGetter(Context context) {
        movieSubject = new BaseMoviesSubject();
        this.context = context;
    }

    @Override
    public void getMovies(String query) {
        ShowsLocalCacheManager.getInstance(context).getShows(this);
    }

    @Override
    public void registerObserver(MoviesObserver moviesObserver){
        movieSubject.registerObserver(moviesObserver);
    }

    @Override
    public void notifyObservers(List<Show> movies){
        movieSubject.notifyObservers(movies);
    }
}
