package com.example.mheshamg.xmovies.view.fagments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.view.activity.NetworkAPIMovieDetailsActivity;
import com.example.mheshamg.xmovies.view.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.presenter.BaseFragmentPresenterClass;
import com.example.mheshamg.xmovies.business.movie_getters.TopRatedMoviesNetworkApiGetter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *  interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class TopRatedFragment extends BaseFragment {

    private static final String TAG=TopRatedFragment.class.getSimpleName();


    public TopRatedFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnMovieClickListener((int position)-> {
            Intent detailsActiviyIntent=new Intent(getContext(), NetworkAPIMovieDetailsActivity.class);
            detailsActiviyIntent.putExtra("Show",movies.get(position));
            startActivity(detailsActiviyIntent);
        });
        baseFragmentPresenter.setMoviesGetter(new TopRatedMoviesNetworkApiGetter());
        MoviesAdapter=new MoviesAdapter(baseFragmentPresenter.getMoviesList(),R.layout.list_item_movie,context,onMovieItemClickListener);
        baseFragmentPresenter.retriveData("none");
    }
}