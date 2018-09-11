package com.example.mheshamg.xmovies.fagments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.presenter.TopRatedFragmentPresenter;
import com.example.mheshamg.xmovies.presenter.UpComingFragmentPresenter;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class UpComingFragment extends BaseFragment implements MoviesAdapter.OnMovieItemClickListener {

    private UpComingFragmentPresenter upComingFragmentPresenter;

    public UpComingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        upComingFragmentPresenter=new UpComingFragmentPresenter();
        upComingFragmentPresenter.setView(this);
        MoviesAdapter=new MoviesAdapter(upComingFragmentPresenter.getMoviesList(),R.layout.list_item_movie,context,this);
        upComingFragmentPresenter.retriveData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        DiscreteScrollView recyclerView = rootView.findViewById(R.id.movies_recycler_view);

        recyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        recyclerView.setAdapter(MoviesAdapter);
        recyclerView.scrollToPosition(5);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void updateView(ArrayList<Movie> movies) {
        MoviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMovieItemClick(int position) {

    }
}
