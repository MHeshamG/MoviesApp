package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.SearchMovieNetworkApiGetter;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;

public class SearchActivityPresenter {

    private static final String TAG=SearchActivityPresenter.class.getSimpleName();
    private SearchViewInterface searchActivity;

    private ArrayList<Movie> moviesList;
    protected SearchMovieNetworkApiGetter moviesGetter;


    public SearchActivityPresenter(SearchViewInterface searchViewInterface) {
        moviesList=new ArrayList<>();
        searchActivity=searchViewInterface;
        moviesGetter=new SearchMovieNetworkApiGetter();
       // moviesGetter.getMoviesResponsePublishSubject().subscribeWith(createTopRatedMoviesDisposableSingleObserver());
    }

    public void retriveData(String query) {
        moviesGetter.setQuery(query);
       // moviesGetter.fetchMovies();
    }

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public DisposableObserver<MoviesResponse> createTopRatedMoviesDisposableSingleObserver() {
        return new DisposableObserver<MoviesResponse>() {

            @Override
            public void onNext(MoviesResponse moviesResponse) {
                Log.i(TAG, moviesResponse.getResults().size() + "");
                moviesList.addAll(moviesResponse.getResults());
                Log.i(TAG,"Data recevied");
               searchActivity.updateView(moviesList);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "completed");
            }
        };
    }

    public interface SearchViewInterface{
        public void updateView(ArrayList<Movie> movies);
    }
}
