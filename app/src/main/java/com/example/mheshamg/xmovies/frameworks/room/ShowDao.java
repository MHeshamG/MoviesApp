package com.example.mheshamg.xmovies.frameworks.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mheshamg.xmovies.model.Show;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface ShowDao {

    @Query("SELECT * FROM shows")
    Flowable<List<Show>> loadAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insertShow(Show show);

    @Delete
     int deleteShow(Show show);

}
