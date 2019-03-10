package com.example.mheshamg.xmovies.room;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.example.mheshamg.xmovies.MoviesGetter;
import com.example.mheshamg.xmovies.MoviesObserver;
import com.example.mheshamg.xmovies.model.Show;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowsLocalCacheManager {
    private static final String TAG = ShowsLocalCacheManager.class.getSimpleName();
    private static final String DB_NAME = "show-database";
    private Context context;
    private static ShowsLocalCacheManager _instance;
    private AppDatabase db;

    public static ShowsLocalCacheManager getInstance(Context context) {
        if (_instance == null) {
            _instance = new ShowsLocalCacheManager(context);
        }
        return _instance;
    }

    public ShowsLocalCacheManager(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public void getShows(final MoviesGetter moviesGetter) {
        db.showDao().loadAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Show>>() {
            @Override
            public void accept(List<Show> shows) throws Exception {
                moviesGetter.notifyObservers(shows);
            }
        });
    }

    public void addShow(Show show) {
        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> emitter) throws Exception {
                Long id = db.showDao().insertShow(show);
                if(!emitter.isDisposed()){
                    emitter.onNext(id);
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.d(TAG,"Inserted with id"+aLong);
            }
        });
    }

    public void deleteShow(Show show) {
        db.showDao().deleteShow(show);
    }
}