package com.example.mheshamg.xmovies.view.fagments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.model.Show;

import java.util.List;

public class NetworkBasedFragment extends BaseFragment {

    @Override
    public void updateView(List<Show> movies) {
        if (movies!=null && !movies.isEmpty()) {
            this.movies = movies;
            MoviesAdapter.notifyDataSetChanged();
            showViews();
        }
        else {
            hideViews();
            new AlertDialog.Builder(getContext())
                    .setTitle("Failed to load movies")
                    .setMessage("Invalid received data")
                    .setPositiveButton("GO BACK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getActivity().finish();
                        }
                    })
                    .show();
        }
    }
}
