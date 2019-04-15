package com.example.mheshamg.xmovies.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.model.Show;
import com.facebook.drawee.view.SimpleDraweeView;

public class BaseMovieDetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    ActionBar actionBar;

    protected Show show;

    private TextView movieTitle;
    private SimpleDraweeView moviePoster;
    private TextView movieDetailsText;
    protected LinearLayout rootLayout;
    private FloatingActionButton floatingActionButton;// may be save button or delete button according to the movie is retrieved
                                                     // from the network api or the database

    private FloatingActionButtonActionInterface floatingActionButtonActionInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent openingIntent=getIntent();
        if(openingIntent!=null){
            show = openingIntent.getParcelableExtra("Show");
        }

        rootLayout = findViewById(R.id.details_container);
        movieTitle = findViewById(R.id.movie_details_title);
        moviePoster = findViewById(R.id.movie_poster_view_details);
        movieDetailsText = findViewById(R.id.movie_details_descrption);
        floatingActionButton = findViewById(R.id.save_btn);

        floatingActionButton.setOnClickListener(view -> {
            //ShowsLocalCacheManager.getInstance(this).addShow(show);
            if (floatingActionButtonActionInterface!=null)
                floatingActionButtonActionInterface.performAction();

            Animation scale = AnimationUtils.loadAnimation(this, R.anim.bounce);
            floatingActionButton.startAnimation(scale);

        });


        updateView(show);
    }

    public void updateView(Show show) {
        if (show !=null) {
            movieTitle.setText(show.getTitle());
            moviePoster.setImageURI(Uri.parse("https://image.tmdb.org/t/p/original" + show.getPosterPath()));
            movieDetailsText.setText(show.getOverview());
        }
    }

    public void setFloatingActionButtonActionInterface(FloatingActionButtonActionInterface floatingActionButtonActionInterface) {
        this.floatingActionButtonActionInterface = floatingActionButtonActionInterface;
    }

    protected void setFloatingActionButtonIcon(int imageResource){
        floatingActionButton.setImageResource(imageResource);
    }

    public interface FloatingActionButtonActionInterface{
        void performAction();
    }

}
