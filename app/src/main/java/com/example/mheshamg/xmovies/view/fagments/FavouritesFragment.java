package com.example.mheshamg.xmovies.view.fagments;

import android.os.Bundle;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.view.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;
import com.example.mheshamg.xmovies.business.room.DatabaseMoviesGetter;

public class FavouritesFragment extends BaseFragment{

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        baseFragmentPresenter=new BaseFragmentPresenterClass();
        baseFragmentPresenter.setMoviesGetter(new DatabaseMoviesGetter(getContext()));
        baseFragmentPresenter.setView(this);
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(), R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData("none");
    }
}
