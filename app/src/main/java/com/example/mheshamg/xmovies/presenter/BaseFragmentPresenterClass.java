package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.MoviesGetter;
import com.example.mheshamg.xmovies.MoviesObserver;
import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class BaseFragmentPresenterClass implements BaseFragmentPresenter {

    private static final String TAG=BaseFragmentPresenterClass.class.getSimpleName();
    private String query;

    protected BaseFragment baseFragment;
    protected ArrayList<Movie> moviesList;
    protected MoviesGetter moviesParser;

    public BaseFragmentPresenterClass() {
        moviesList=new ArrayList<>();
    }

    @Override
    public void setView(BaseFragment baseFragment)
    {
        this.baseFragment=baseFragment;
    }

    @Override
    public void retriveData() {
        moviesParser.registerObserver(new MoviesObserver() {
            @Override
            public void moviesRetrived(List<Movie> movies) {
                moviesList.addAll(movies);
                baseFragment.updateView(movies);
            }
        });
        moviesParser.getMovies(baseFragment.getQuery());
    }

    @Override
    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    @Override
    public void setMoviesGetter(MoviesGetter moviesGetter) {
        moviesParser = moviesGetter;
    }
}
