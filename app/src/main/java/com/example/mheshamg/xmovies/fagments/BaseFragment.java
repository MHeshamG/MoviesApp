package com.example.mheshamg.xmovies.fagments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.model.Movie;

import java.util.ArrayList;

public abstract class BaseFragment extends Fragment{

    protected Context context;
    protected RecyclerView recyclerView ;
    protected MoviesAdapter MoviesAdapter;
    protected ArrayList<Movie> movies;




    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public abstract void updateView(ArrayList<Movie>movies);
}
