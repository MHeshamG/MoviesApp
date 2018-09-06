package com.example.mheshamg.xmovies.fagments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.presenter.TopRatedFragmentPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *  interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class TopRatedFragment extends BaseFragment implements MoviesAdapter.OnMovieItemClickListener {

    private Context context;
    private RecyclerView recyclerView ;
    private MoviesAdapter topRatedMoviesAdapter;
    private ArrayList<Movie> movies;

    private TopRatedFragmentPresenter topRatedFragmentPresenter;

    public TopRatedFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
        topRatedFragmentPresenter=new TopRatedFragmentPresenter();
        topRatedFragmentPresenter.setView(this);
        topRatedMoviesAdapter=new MoviesAdapter(topRatedFragmentPresenter.getMoviesList(),R.layout.list_item_movie,context,this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_top_rated_movies, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(topRatedMoviesAdapter);
        topRatedFragmentPresenter.retriveData();
        // Inflate the layout for this fragment
        return rootView;
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
    public void onMovieItemClick(int position) {
        Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView(ArrayList<Movie> movies) {
        topRatedMoviesAdapter.notifyDataSetChanged();
    }
}