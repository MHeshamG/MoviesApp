package com.example.mheshamg.xmovies.room;

import android.content.Context;

import com.example.mheshamg.xmovies.BaseMoviesSubject;
import com.example.mheshamg.xmovies.MovieSubject;
import com.example.mheshamg.xmovies.MoviesGetter;
import com.example.mheshamg.xmovies.MoviesObserver;
import com.example.mheshamg.xmovies.model.Show;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

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
