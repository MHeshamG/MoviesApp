package com.example.mheshamg.xmovies.presenter;

import android.util.Log;

import com.example.mheshamg.xmovies.business.MoviesGetter;
import com.example.mheshamg.xmovies.business.MoviesObserver;
import com.example.mheshamg.xmovies.view.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Show;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class BaseFragmentPresenterClass implements BaseFragmentPresenter {

    private static final String TAG=BaseFragmentPresenterClass.class.getSimpleName();

    protected BaseFragment baseFragment;
    protected ArrayList<Show> moviesList;
    protected MoviesGetter moviesParser;

    private Observable movieGetterInterval;
    private Disposable movieIntervalDisposable;
    private PublishSubject<Boolean> movieRetrievedPublishSubject;


    public BaseFragmentPresenterClass() {
        moviesList=new ArrayList<>();
        movieGetterInterval = Observable.interval(1,TimeUnit.SECONDS);
        movieRetrievedPublishSubject = PublishSubject.create();
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
                movieIntervalDisposable.dispose();
            }
        });
        moviesParser.getMovies(query);
        movieGetterInterval.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                movieIntervalDisposable = d;
            }

            @Override
            public void onNext(Long l) {
                Log.d("XXX",""+l);
                if(l==10){
                    Log.d("XXXZ",""+l);
                    movieRetrievedPublishSubject.onNext(Boolean.FALSE);
                    movieIntervalDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public ArrayList<Show> getMoviesList() {
        return moviesList;
    }

    @Override
    public void setMoviesGetter(MoviesGetter moviesGetter) {
        moviesParser = moviesGetter;
    }

    @Override
    public void subscribeToMovieRetrievedPublishSubject(Observer o){
        movieRetrievedPublishSubject.subscribe(o);
    }

    public void restartInterval(){
        movieGetterInterval.startWith(0L);
    }

}
