package com.example.mheshamg.xmovies.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.model.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private ArrayList<Movie> movies;
    private int rowLayout;
    private Context context;
    private OnMovieItemClickListener onMovieItemClickListener;


    public MoviesAdapter(ArrayList<Movie> movies, int rowLayout, Context context,OnMovieItemClickListener onMovieItemClickListener) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.onMovieItemClickListener=onMovieItemClickListener;

    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
       bindMovieItemWithView(holder,position);
    }

    /**
     *
     * @param holder
     * @param position
     */
    private void bindMovieItemWithView(MovieViewHolder holder,int position)
    {
        holder.moviePosterDraweeView.setImageURI(Uri.parse("https://image.tmdb.org/t/p/original"+movies.get(position).getPosterPath()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    /**********************************************************************
     *********************MovieHolder class********************************
     **********************************************************************/
    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        SimpleDraweeView moviePosterDraweeView;



        public MovieViewHolder(View v) {
            super(v);
            moviePosterDraweeView=(SimpleDraweeView) v.findViewById(R.id.movie_poster_view);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemPosition=getAdapterPosition();
            if(onMovieItemClickListener!=null)
                onMovieItemClickListener.onMovieItemClick(itemPosition);
        }
    }

    /**********************************************************************
     **********************OnMovieItemClickListener************************
     **********************************************************************/
    public interface OnMovieItemClickListener
    {
        public void onMovieItemClick(int position);
    }
}