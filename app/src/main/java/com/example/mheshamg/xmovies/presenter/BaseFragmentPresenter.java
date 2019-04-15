package com.example.mheshamg.xmovies.presenter;


import com.example.mheshamg.xmovies.business.MoviesGetter;
import com.example.mheshamg.xmovies.view.fagments.BaseFragment;
import com.example.mheshamg.xmovies.model.Show;

import java.util.ArrayList;

import io.reactivex.Observer;

public interface BaseFragmentPresenter {

     void retriveData(String query);
     void setView(BaseFragment baseFragment);
     ArrayList<Show> getMoviesList();
     void setMoviesGetter(MoviesGetter moviesGetter);
     void subscribeToMovieRetrievedPublishSubject(Observer o);
     void restartInterval();

}
