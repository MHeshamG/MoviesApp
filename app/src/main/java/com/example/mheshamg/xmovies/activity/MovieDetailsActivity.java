package com.example.mheshamg.xmovies.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MovieDetails;
import com.example.mheshamg.xmovies.presenter.MovieDetailsActivityPresenter;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsActivityPresenter.MovieDetailsViewInterface {

    private Toolbar mToolbar;
    ActionBar actionBar;

    private MovieDetailsActivityPresenter movieDetailsActivityPresenter;

    private TextView movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        movieDetailsActivityPresenter=new MovieDetailsActivityPresenter(this);
        movieDetailsActivityPresenter.setMovieDetailsViewInterface(this);
       // movieDetailsActivityPresenter.

        movieTitle=findViewById(R.id.movie_details_title);
    }

    @Override
    public void updateView(MovieDetails movieDetails) {
        movieTitle.setText(movieDetails.getTitle());
    }
}
