package com.example.mheshamg.xmovies.fagments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.activity.MovieDetailsActivity;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenter;
import com.example.mheshamg.xmovies.presenter.MainActivityPresenter;
import com.example.mheshamg.xmovies.utils.DateFormater;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public abstract class BaseFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>,View.OnClickListener{

    protected Context context;
    protected RecyclerView recyclerView ;
    protected MoviesAdapter MoviesAdapter;
    protected ArrayList<Movie> movies;
    protected BaseFragmentPresenter baseFragmentPresenter;
    protected MainActivityPresenter mainActivityPresenter;

    private TextView title;
    private TextView rating;
    private TextView popularity;
    private TextView date;
    private TextView detailsButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        DiscreteScrollView recyclerView = rootView.findViewById(R.id.movies_recycler_view);
        title=(TextView) rootView.findViewById(R.id.title);
        rating=(TextView) rootView.findViewById(R.id.rating);
        popularity=(TextView) rootView.findViewById(R.id.popularity);
        date=(TextView) rootView.findViewById(R.id.subtitle);
        detailsButton=(TextView) rootView.findViewById(R.id.details_button);
        detailsButton.setOnClickListener(this);

        recyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        recyclerView.setAdapter(MoviesAdapter);
        recyclerView.addOnItemChangedListener(this);

        return rootView;
    }

    public void updateView(ArrayList<Movie> movies) {
        this.movies=movies;
        MoviesAdapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        title.setText(movies.get(adapterPosition).getTitle());
        rating.setText(movies.get(adapterPosition).getVoteAverage().toString());
        popularity.setText(""+movies.get(adapterPosition).getPopularity().intValue());
        date.setText(DateFormater.changeFormat(movies.get(adapterPosition).getReleaseDate()));

    }

    public void setMainActivityPresenter(MainActivityPresenter mainActivityPresenter){
        this.mainActivityPresenter=mainActivityPresenter;
    }

    @Override
    public void onClick(View view) {
        Intent i=new Intent(getContext(), MovieDetailsActivity.class);
        startActivity(i);
    }
}
