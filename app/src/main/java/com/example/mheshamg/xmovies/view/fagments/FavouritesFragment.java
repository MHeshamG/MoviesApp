package com.example.mheshamg.xmovies.view.fagments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.model.Show;
import com.example.mheshamg.xmovies.view.activity.FavoriteDetailsActivity;
import com.example.mheshamg.xmovies.view.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;
import com.example.mheshamg.xmovies.business.movie_getters.DatabaseMoviesGetter;

import java.util.List;

public class FavouritesFragment extends BaseFragment{

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnMovieClickListener(position-> {
            Intent detailsActiviyIntent=new Intent(getContext(), FavoriteDetailsActivity.class);
            detailsActiviyIntent.putExtra("Show",movies.get(position));
            startActivity(detailsActiviyIntent);
        });
        context=getContext();
        baseFragmentPresenter=new BaseFragmentPresenterClass();
        baseFragmentPresenter.setMoviesGetter(new DatabaseMoviesGetter(getContext()));
        baseFragmentPresenter.setView(this);
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(), R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData("none");
    }

    @Override
    public void updateView(List<Show> movies) {
        if (movies!=null && !movies.isEmpty()) {
            this.movies = movies;
            MoviesAdapter.notifyDataSetChanged();
            showViews();
        }
        else {
            hideViews();
            new AlertDialog.Builder(getContext())
                    .setTitle("No Movies Saved")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .show();
        }
    }
    }
