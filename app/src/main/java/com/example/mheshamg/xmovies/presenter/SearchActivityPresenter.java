package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.MoviesGetter;
import com.example.mheshamg.xmovies.MoviesObserver;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.rest.SearchMoviesNetworkApiGetter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivityPresenter {

    private static final String TAG=SearchActivityPresenter.class.getSimpleName();
    private SearchViewInterface searchActivity;

    private ArrayList<Movie> moviesList;
    protected MoviesGetter moviesGetter;


    public SearchActivityPresenter(SearchViewInterface searchViewInterface) {
        moviesList=new ArrayList<>();
        searchActivity=searchViewInterface;
        moviesGetter=new SearchMoviesNetworkApiGetter();
    }

    public void retriveData(String query) {
        moviesGetter.registerObserver(new MoviesObserver() {
            @Override
            public void moviesRetrived(List<Movie> movies) {
                Log.i("xxxz",movies.get(0).getTitle());
                moviesList.addAll(movies);
                searchActivity.updateView(moviesList);
            }
        });
        moviesGetter.getMovies(query);
    }

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public interface SearchViewInterface{
        void updateView(ArrayList<Movie> movies);
    }
}
