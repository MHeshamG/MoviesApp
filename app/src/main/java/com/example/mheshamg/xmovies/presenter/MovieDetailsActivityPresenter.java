package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MovieDetails;
import com.example.mheshamg.xmovies.rest.MovieDetailsGetter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;


public class MovieDetailsActivityPresenter {

    private static final String TAG=MovieDetailsActivityPresenter.class.getSimpleName();

    private MovieDetailsGetter movieDetailsGetter;
    private MovieDetails movieDetails;
    private MovieDetailsViewInterface movieDetailsViewInterface;
    private DisposableObserver<MovieDetails> moviesDetailsDisposableObserver;
    private PublishSubject<MovieDetails> movieDetailsPublishSubject;

    public MovieDetailsActivityPresenter(MovieDetailsViewInterface movieDetailsViewInterface,Long movieId)
    {
        this.movieDetailsViewInterface = movieDetailsViewInterface;
        movieDetailsGetter=new MovieDetailsGetter();
        movieDetailsPublishSubject=movieDetailsGetter.getMoviesResponsePublishSubject();
        moviesDetailsDisposableObserver=getMoviesDetailsDisposableObserver();
        movieDetailsPublishSubject.subscribeOn(Schedulers.io())
                                  .observeOn(AndroidSchedulers.mainThread())
                                  .subscribeWith(moviesDetailsDisposableObserver);
        movieDetailsGetter.getMovieDetails(movieId);
    }

    DisposableObserver<MovieDetails> getMoviesDetailsDisposableObserver(){
        return new DisposableObserver<MovieDetails>() {
            @Override
            public void onNext(MovieDetails movieDetails) {
                movieDetailsViewInterface.updateView(movieDetails);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG,"completed");
            }
        };
    }


    public interface MovieDetailsViewInterface
    {
        public void updateView (MovieDetails movieDetails);
    }
}
