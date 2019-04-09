package com.example.mheshamg.xmovies.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.model.Show;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private ArrayList<Show> shows;
    private int rowLayout;
    private Context context;
    private OnMovieItemClickListener onMovieItemClickListener;


    public MoviesAdapter(ArrayList<Show> shows, int rowLayout, Context context, OnMovieItemClickListener onMovieItemClickListener) {
        this.shows = shows;
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
        holder.moviePosterDraweeView.setImageURI(Uri.parse("https://image.tmdb.org/t/p/original"+ shows.get(position).getPosterPath()));
    }

    @Override
    public int getItemCount() {
        return shows.size();
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