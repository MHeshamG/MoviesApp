package com.example.mheshamg.xmovies.view.fagments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.business.movie_getters.UpComingMoviesNetworkApiGetter;
import com.example.mheshamg.xmovies.model.Show;
import com.example.mheshamg.xmovies.view.activity.NetworkAPIMovieDetailsActivity;
import com.example.mheshamg.xmovies.view.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;

import java.util.List;

public class UpComingFragment extends BaseFragment {

    public UpComingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnMovieClickListener((int position)-> {
            Intent detailsActiviyIntent=new Intent(getContext(), NetworkAPIMovieDetailsActivity.class);
            detailsActiviyIntent.putExtra("Show",movies.get(position));
            startActivity(detailsActiviyIntent);
        });
        baseFragmentPresenter.setMoviesGetter(new UpComingMoviesNetworkApiGetter());
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(),R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData("none");

    }
}
