package com.example.mheshamg.xmovies.presenter;


import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Movie;

import java.util.ArrayList;

public interface BaseFragmentPresenter {

    public void retriveData();
    public void setView(BaseFragment baseFragment);
    public ArrayList<Movie> getMoviesList();

}
