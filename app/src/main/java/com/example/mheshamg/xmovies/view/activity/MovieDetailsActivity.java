package com.example.mheshamg.xmovies.view.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.transition.ChangeBounds;
import android.support.transition.ChangeImageTransform;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.model.Show;
import com.example.mheshamg.xmovies.business.room.ShowsLocalCacheManager;
import com.facebook.drawee.view.SimpleDraweeView;

public class MovieDetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    ActionBar actionBar;

   private Show show;

    private TextView movieTitle;
    private SimpleDraweeView moviePoster;
    private TextView movieDetailsText;
    private ImageButton saveBtn;
    private LinearLayout rootLayout;
    private boolean isPosterExpanded;
    private CardView posterCardView;


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

        rootLayout = findViewById(R.id.details_container);
        movieTitle = findViewById(R.id.movie_details_title);
        posterCardView = findViewById(R.id.poster_card_view);
        moviePoster = findViewById(R.id.movie_poster_view_details);
        movieDetailsText = findViewById(R.id.movie_details_descrption);
        movieDetailsText.setMovementMethod(new ScrollingMovementMethod());
        saveBtn = findViewById(R.id.save_btn);

        saveBtn.setOnClickListener(view -> {
            ShowsLocalCacheManager.getInstance(this).addShow(show);
            Animation scale = AnimationUtils.loadAnimation(this, R.anim.bounce);
          saveBtn.startAnimation(scale);
            Snackbar snackbar=  Snackbar.make(rootLayout,"Added To Favourites",Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(MovieDetailsActivity.this, R.color.colorPrimary));
            snackbar.show();
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

}
