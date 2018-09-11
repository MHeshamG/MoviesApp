package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.PopularMoviesParser;
import com.example.mheshamg.xmovies.rest.UpComingMoviesParser;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;

public class PopularFragmentPresenter implements BaseFragmentPresenter {
    private final static String TAG=UpComingFragmentPresenter.class.getSimpleName();
    private BaseFragment upComingFragment;

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    private ArrayList<Movie> moviesList;

    //Network
    private PopularMoviesParser popularMoviesParser;
    private DisposableObserver<MoviesResponse> popularMoviesDisposableObserver;

    public PopularFragmentPresenter()
    {
        popularMoviesParser=new PopularMoviesParser();
        moviesList=new ArrayList<>();
        popularMoviesDisposableObserver=createTopRatedMoviesDisposableSingleObserver();
        popularMoviesParser.getMoviesResponsePublishSubject().subscribeWith(popularMoviesDisposableObserver);
    }

    public void setView(BaseFragment baseFragment)
    {
        upComingFragment=baseFragment;
    }

    @Override
    public void retriveData() {
        popularMoviesParser.fetchTopRatedMovies();
    }

    public DisposableObserver<MoviesResponse> createTopRatedMoviesDisposableSingleObserver() {
        return new DisposableObserver<MoviesResponse>() {

            @Override
            public void onNext(MoviesResponse moviesResponse) {
                Log.i(TAG, moviesResponse.getResults().size() + "");
                moviesList.addAll(moviesResponse.getResults());
                upComingFragment.updateView(moviesList);
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
