package com.example.mheshamg.xmovies.fagments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.presenter.TopRatedFragmentPresenter;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

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

    private static final String TAG=TopRatedFragment.class.getSimpleName();

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
        MoviesAdapter=new MoviesAdapter(topRatedFragmentPresenter.getMoviesList(),R.layout.list_item_movie,context,this);
        topRatedFragmentPresenter.retriveData();
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
    public void onMovieItemClick(int position) {
        Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView(ArrayList<Movie> movies) {
        MoviesAdapter.notifyDataSetChanged();
    }
}