package com.example.mheshamg.xmovies.view.fagments;

import android.os.Bundle;
import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.view.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;
import com.example.mheshamg.xmovies.business.movie_getters.rest.UpComingMoviesNetworkApiGetter;

public class UpComingFragment extends BaseFragment implements MoviesAdapter.OnMovieItemClickListener {

    public UpComingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        baseFragmentPresenter=new BaseFragmentPresenterClass();
        baseFragmentPresenter.setMoviesGetter(new UpComingMoviesNetworkApiGetter());
        baseFragmentPresenter.setView(this);
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(),R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData("none");
    }


    @Override
    public void onMovieItemClick(int position) {

    }
}
