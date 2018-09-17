package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.BaseParser;
import com.example.mheshamg.xmovies.rest.PopularMoviesParser;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;

public class BaseFragmentPresenterClass implements BaseFragmentPresenter {

    private static final String TAG=BaseFragmentPresenterClass.class.getSimpleName();
    private MainActivityPresenter mainActivityPresenter;

    protected BaseFragment baseFragment;
    protected ArrayList<Movie> moviesList;
    protected BaseParser moviesParser;


    public BaseFragmentPresenterClass(MainActivityPresenter mainActivityPresenter) {
        moviesList=new ArrayList<>();
        this.mainActivityPresenter=mainActivityPresenter;
    }

    @Override
    public void setView(BaseFragment baseFragment)
    {
        this.baseFragment=baseFragment;
    }


    @Override
    public void retriveData() {
        moviesParser.fetchTopRatedMovies();
    }

    @Override
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
                baseFragment.updateView(moviesList);
                mainActivityPresenter.proceedSplashScreen();
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
}
