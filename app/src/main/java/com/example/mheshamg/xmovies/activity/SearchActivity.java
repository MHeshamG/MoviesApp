package com.example.mheshamg.xmovies.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.presenter.SearchActivityPresenter;
import com.example.mheshamg.xmovies.utils.DateFormater;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity  implements MoviesAdapter.OnMovieItemClickListener,SearchActivityPresenter.SearchViewInterface , DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>{

    private Toolbar mToolbar;
    ActionBar actionBar;
    private SearchActivityPresenter searchActivityPresenter;

    protected MoviesAdapter moviesAdapter;
    protected ArrayList<Movie> movies;

    private TextView title;
    private TextView rating;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        if (intent!=null){
            String query=intent.getStringExtra(SearchManager.QUERY);
            searchActivityPresenter=new SearchActivityPresenter(this);
            searchActivityPresenter.retriveData(query);
        }

        moviesAdapter=new MoviesAdapter(searchActivityPresenter.getMoviesList(), R.layout.list_item_movie,this,this);
        DiscreteScrollView recyclerView = findViewById(R.id.movies_recycler_view);
        title = findViewById(R.id.title);
        rating = findViewById(R.id.rating);
        date = findViewById(R.id.subtitle);

        recyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        recyclerView.setAdapter(moviesAdapter);
        recyclerView.addOnItemChangedListener(this);
    }

    @Override
    public void updateView(ArrayList<Movie> movies) {
        this.movies=movies;
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        if (movies.size()>0) {
            title.setText(movies.get(adapterPosition).getTitle());
            rating.setText("" + movies.get(adapterPosition).getVoteAverage());
            date.setText(DateFormater.changeFormat(movies.get(adapterPosition).getReleaseDate()));
        }
    }

    @Override
    public void onMovieItemClick(int position) {

    }
}
