package com.example.mheshamg.xmovies.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.rest.TopRatedMoviesParser;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;

public class MainActivityPresenter
{

    private final static  String TAG=MainActivityPresenter.class.getSimpleName();

    private final static String API_KEY = "134da0f4a51a36498831652090ad9aaf";

    private MainActivityViewInterface mMainActivityViewInterface;
    private Context mContext;
    private ArrayList<Movie> moviesList;

    private TopRatedMoviesParser topRatedMoviesParser;
    private DisposableObserver<MoviesResponse> topRatedMoviesDisposableObserver;



    private MoviesAdapter moviesAdapter;


    public MainActivityPresenter(MainActivityViewInterface mainActivityViewInterface ,Context context)
    {
        this.mMainActivityViewInterface=mainActivityViewInterface;
        this.mContext=context;
    }

    public boolean initialize()
    {
        if (API_KEY.isEmpty()) {
            Toast.makeText(mContext, "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return false;
        }
        Fresco.initialize(mContext);
        topRatedMoviesParser=new TopRatedMoviesParser();
        moviesList=new ArrayList<>();
        moviesAdapter=new MoviesAdapter(moviesList, R.layout.list_item_movie,mContext,null);
        topRatedMoviesDisposableObserver=createTopRatedMoviesDisposableSingleObserver();
        topRatedMoviesParser.getMoviesResponsePublishSubject().subscribeWith(topRatedMoviesDisposableObserver);

        return true;
    }

    public void fetchTopRatedMovies()
    {
        topRatedMoviesParser.fetchTopRatedMovies();
    }


    /**
     *
     * @return
     */
    public MoviesAdapter getMoviesAdapter() {
        return moviesAdapter;
    }

    public DisposableObserver<MoviesResponse> createTopRatedMoviesDisposableSingleObserver()
    {
        return new DisposableObserver<MoviesResponse>() {


            @Override
            public void onNext(MoviesResponse moviesResponse) {
                moviesList.addAll(moviesResponse.getResults());
                moviesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG,"completed");
            }
        };
    }



    /**
     *
     */
    public void disposeMoviesObserver()
    {

    }

    /**
     *
     *
     */
    public interface MainActivityViewInterface
    {
        public void updateRecyclerView ();
    }
}
