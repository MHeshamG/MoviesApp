package com.example.mheshamg.xmovies.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.model.Show;
import com.facebook.drawee.view.SimpleDraweeView;

public class MovieDetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    ActionBar actionBar;

   private Show show;

    private TextView movieTitle;
    private SimpleDraweeView moviePoster;
    private TextView movieDetailsText;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent openingIntent=getIntent();
        if(openingIntent!=null){
            show = openingIntent.getParcelableExtra("Show");
        }

        movieTitle=findViewById(R.id.movie_details_title);
        moviePoster=findViewById(R.id.movie_poster_view_details);
        movieDetailsText=findViewById(R.id.movie_details_descrption);

        updateView(show);
    }

    public void updateView(Show show) {
        if (show !=null) {
            movieTitle.setText(show.getTitle());
            moviePoster.setImageURI(Uri.parse("https://image.tmdb.org/t/p/original" + show.getPosterPath()));
            movieDetailsText.setText(show.getOverview());
        }
    }
}
