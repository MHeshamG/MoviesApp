package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.TopRatedMoviesParser;
import com.example.mheshamg.xmovies.rest.UpComingMoviesParser;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;

public class UpComingFragmentPresenter implements BaseFragmentPresenter {

    private final static String TAG=UpComingFragmentPresenter.class.getSimpleName();
    private BaseFragment upComingFragment;

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    private ArrayList<Movie> moviesList;

    //Network
    private UpComingMoviesParser upComingMoviesParser;
    private DisposableObserver<MoviesResponse> upComingMoviesDisposableObserver;

    public UpComingFragmentPresenter()
    {
        upComingMoviesParser=new UpComingMoviesParser();
        moviesList=new ArrayList<>();
        upComingMoviesDisposableObserver=createTopRatedMoviesDisposableSingleObserver();
        upComingMoviesParser.getMoviesResponsePublishSubject().subscribeWith(upComingMoviesDisposableObserver);
    }

    public void setView(BaseFragment baseFragment)
    {
        upComingFragment=baseFragment;
    }

    @Override
    public void retriveData() {
        upComingMoviesParser.fetchTopRatedMovies();
    }

    public DisposableObserver<MoviesResponse> createTopRatedMoviesDisposableSingleObserver()
    {
        return new DisposableObserver<MoviesResponse>() {

            @Override
            public void onNext(MoviesResponse moviesResponse) {
                Log.i(TAG,moviesResponse.getResults().size()+"");
                moviesList.addAll(moviesResponse.getResults());
                upComingFragment.updateView(moviesList);
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
