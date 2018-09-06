package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.TopRatedMoviesParser;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;


public class TopRatedFragmentPresenter implements BaseFragmentPresenter {

    private final static String TAG=TopRatedFragmentPresenter.class.getSimpleName();
    private BaseFragment topRatedFragment;

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    private ArrayList<Movie> moviesList;

    //Network
    private TopRatedMoviesParser topRatedMoviesParser;
    private DisposableObserver<MoviesResponse> topRatedMoviesDisposableObserver;

    public TopRatedFragmentPresenter()
    {
        topRatedMoviesParser=new TopRatedMoviesParser();
        moviesList=new ArrayList<>();
        topRatedMoviesDisposableObserver=createTopRatedMoviesDisposableSingleObserver();
        topRatedMoviesParser.getMoviesResponsePublishSubject().subscribeWith(topRatedMoviesDisposableObserver);
    }

    public void setView(BaseFragment baseFragment)
    {
        topRatedFragment=baseFragment;
    }

    @Override
    public void retriveData() {
        topRatedMoviesParser.fetchTopRatedMovies();
    }

    public DisposableObserver<MoviesResponse> createTopRatedMoviesDisposableSingleObserver()
    {
        return new DisposableObserver<MoviesResponse>() {

            @Override
            public void onNext(MoviesResponse moviesResponse) {
                Log.i(TAG,moviesResponse.getResults().size()+"");
                moviesList.addAll(moviesResponse.getResults());
                topRatedFragment.updateView(moviesList);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"completed");
            }
        };
    }
}
