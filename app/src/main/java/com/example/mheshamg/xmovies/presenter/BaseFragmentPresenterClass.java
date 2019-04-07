package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.business.MoviesGetter;
import com.example.mheshamg.xmovies.business.MoviesObserver;
import com.example.mheshamg.xmovies.view.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Show;

import java.util.ArrayList;
import java.util.List;

public class BaseFragmentPresenterClass implements BaseFragmentPresenter {

    private static final String TAG=BaseFragmentPresenterClass.class.getSimpleName();

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
    public void retriveData(String query) {
        moviesParser.registerObserver(new MoviesObserver() {
            @Override
            public void moviesRetrived(List<Show> movies) {
                moviesList.clear();
                moviesList.addAll(movies);
                baseFragment.updateView(movies);
            }
        });
        moviesParser.getMovies(query);
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
