package com.example.mheshamg.xmovies.view.fagments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.view.activity.BaseMovieDetailsActivity;
import com.example.mheshamg.xmovies.view.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.model.Show;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenter;
import com.example.mheshamg.xmovies.utils.DateFormater;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

public abstract class BaseFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>{

    protected Context context;
    protected MoviesAdapter MoviesAdapter;
    protected List<Show> movies;
    protected BaseFragmentPresenter baseFragmentPresenter;
    protected String query;

    private ProgressBar loadingProgressBar;
    private DiscreteScrollView recyclerView;
    private RelativeLayout dataRelativeLayout;
    private TextView title;
    private TextView rating;
    private TextView date;

    private OnMovieClickListener onMovieClickListener;

    private boolean created=false;
    protected MoviesAdapter.OnMovieItemClickListener onMovieItemClickListener = new MoviesAdapter.OnMovieItemClickListener() {
        @Override
        public void onMovieItemClick(int position) {
           if (onMovieClickListener!=null)
               onMovieClickListener.performAction(position);
        }
    };

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        loadingProgressBar=rootView.findViewById(R.id.loading_progress_bar);
        recyclerView = rootView.findViewById(R.id.movies_recycler_view);
        dataRelativeLayout=rootView.findViewById(R.id.movie_data_main_activity);
        title=(TextView) rootView.findViewById(R.id.title);
        rating=(TextView) rootView.findViewById(R.id.rating);
        date=(TextView) rootView.findViewById(R.id.subtitle);

        recyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        recyclerView.setAdapter(MoviesAdapter);
        recyclerView.addOnItemChangedListener(this);

        if(created)
            showViews();

        return rootView;
    }

    public abstract void updateView(List<Show> movies);

    protected void showViews() {
        hideProgressBar();
        recyclerView.setVisibility(View.VISIBLE);
        dataRelativeLayout.setVisibility(View.VISIBLE);
        created=true;
    }

    protected void hideViews(){
        recyclerView.setVisibility(View.INVISIBLE);
        dataRelativeLayout.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        if(adapterPosition>=0){
            title.setText(movies.get(adapterPosition).getTitle());
            rating.setText(movies.get(adapterPosition).getVoteAverage().toString());
            date.setText(DateFormater.changeFormat(movies.get(adapterPosition).getReleaseDate()));
        }
    }

    protected void hideProgressBar(){
        loadingProgressBar.setVisibility(View.GONE);
    }

    public void showProgressBar(){
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public interface OnMovieClickListener{
        void performAction(int position);
    }
}
