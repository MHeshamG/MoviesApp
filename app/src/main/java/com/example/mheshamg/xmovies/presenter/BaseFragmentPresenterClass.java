package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.MoviesGetter;
import com.example.mheshamg.xmovies.MoviesObserver;
import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Show;

import java.util.ArrayList;
import java.util.List;

public class BaseFragmentPresenterClass implements BaseFragmentPresenter {

    private static final String TAG=BaseFragmentPresenterClass.class.getSimpleName();
    private String query;

    protected BaseFragment baseFragment;
    protected ArrayList<Show> moviesList;
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
            public void moviesRetrived(List<Show> shows) {
                moviesList.addAll(shows);
                baseFragment.updateView(shows);
            }
        });
        moviesParser.getMovies();
    }

    @Override
    public ArrayList<Show> getMoviesList() {
        return moviesList;
    }

    @Override
    public void setMoviesGetter(MoviesGetter moviesGetter) {
        moviesParser = moviesGetter;
    }
}
