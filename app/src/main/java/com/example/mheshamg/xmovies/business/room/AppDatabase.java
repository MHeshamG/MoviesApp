package com.example.mheshamg.xmovies.business.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.mheshamg.xmovies.model.Show;

@Database(entities = {Show.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShowDao showDao();
}