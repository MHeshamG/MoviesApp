package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MovieDetails;

import io.reactivex.observers.DisposableSingleObserver;


public class MovieDetailsActivityPresenter {

    private static final String TAG=MovieDetailsActivityPresenter.class.getSimpleName();

    private MovieDetailsViewInterface movieDetailsViewInterface;
    private DisposableSingleObserver<MovieDetails> moviesDetailsDisposableSingleObserver;

    public MovieDetailsActivityPresenter(MovieDetailsViewInterface movieDetailsViewInterface)
    {
        this.movieDetailsViewInterface = movieDetailsViewInterface;
    }


    public void setMovieDetailsViewInterface(MovieDetailsViewInterface movieDetailsViewInterface) {
        this.movieDetailsViewInterface = movieDetailsViewInterface;
    }

    DisposableSingleObserver<MovieDetails> moviesDetailsDisposableSingleObserver(){
        return new DisposableSingleObserver<MovieDetails>() {
            @Override
            public void onSuccess(MovieDetails movieDetails) {
                movieDetailsViewInterface.updateView(movieDetails);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }
        };
    }


    public interface MovieDetailsViewInterface
    {
        public void updateView (MovieDetails movieDetails);
    }
}
