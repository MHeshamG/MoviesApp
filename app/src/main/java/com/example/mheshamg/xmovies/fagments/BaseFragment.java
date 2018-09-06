package com.example.mheshamg.xmovies.fagments;

import android.support.v4.app.Fragment;

import com.example.mheshamg.xmovies.model.Movie;

import java.util.ArrayList;

public abstract class BaseFragment extends Fragment{
    public abstract void updateView(ArrayList<Movie>movies);
}
