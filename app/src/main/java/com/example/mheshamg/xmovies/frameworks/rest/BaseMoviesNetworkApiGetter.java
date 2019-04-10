package com.example.mheshamg.xmovies.frameworks.rest;

import android.util.Log;

import com.example.mheshamg.xmovies.business.BaseMoviesSubject;
import com.example.mheshamg.xmovies.business.MovieSubject;
import com.example.mheshamg.xmovies.business.MoviesObserver;
import com.example.mheshamg.xmovies.business.MoviesGetter;
 import com.example.mheshamg.xmovies.model.ShowsResponse;
import com.example.mheshamg.xmovies.model.Show;
import java.util.List;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseMoviesNetworkApiGetter implements MoviesGetter {

    private final static String TAG = BaseMoviesNetworkApiGetter.class.getSimpleName();

    protected ApiInterface apiService;
    protected DisposableSingleObserver<ShowsResponse> moviesResponseDisposableSingleObserver;
    private MovieSubject movieSubject;

    public BaseMoviesNetworkApiGetter()
    {
        moviesResponseDisposableSingleObserver=getMoviesResponseObserver();
        apiService=ApiClient.getApiInterface();
        movieSubject = new BaseMoviesSubject();
    }


    public DisposableSingleObserver<ShowsResponse> getMoviesResponseObserver()
    {
        return new DisposableSingleObserver<ShowsResponse>() {
            @Override
            public void onSuccess(ShowsResponse showsResponse) {
                Log.d("hhh",""+ showsResponse.getResults().size());
                notifyObservers(showsResponse.getResults());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }
        };
    }

    public void bindObserverToObservable(Single<ShowsResponse> moviesResponseSingleObservable){
        moviesResponseSingleObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(moviesResponseDisposableSingleObserver);
    }

    @Override
    public void registerObserver(MoviesObserver moviesObserver){
        movieSubject.registerObserver(moviesObserver);
    }

    @Override
    public void notifyObservers(List<Show> movies){
        movieSubject.notifyObservers(movies);
    }
}
