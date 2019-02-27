package com.example.mheshamg.xmovies.presenter;


import com.example.mheshamg.xmovies.MoviesGetter;
import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Show;

import java.util.ArrayList;

public interface BaseFragmentPresenter {

     void retriveData();
     void setView(BaseFragment baseFragment);
     ArrayList<Show> getMoviesList();
     void setMoviesGetter(MoviesGetter moviesGetter);
}
