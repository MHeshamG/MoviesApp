package com.example.mheshamg.xmovies.view.fagments;

import android.os.Bundle;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.view.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;
import com.example.mheshamg.xmovies.business.rest.SearchMoviesNetworkApiGetter;


public class SearchFragment extends BaseFragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        baseFragmentPresenter=new BaseFragmentPresenterClass();
        baseFragmentPresenter.setMoviesGetter(new SearchMoviesNetworkApiGetter());
        baseFragmentPresenter.setView(this);
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(), R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData(getQuery());
    }
}
