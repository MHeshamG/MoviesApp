package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.MoviesGetter;
import com.example.mheshamg.xmovies.MoviesObserver;
import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.BaseMoviesNetworkApiGetter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class BaseFragmentPresenterClass implements BaseFragmentPresenter {

    private static final String TAG=BaseFragmentPresenterClass.class.getSimpleName();

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
    public void retriveData(String query) {
        moviesParser.registerObserver(new MoviesObserver() {
            @Override
            public void moviesRetrived(List<Movie> movies) {
                Log.i("xxx",movies.get(0).getTitle());
                moviesList.addAll(movies);
                baseFragment.updateView(movies);
            }
        });
        moviesParser.getMovies(query);
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
