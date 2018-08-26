
package com.example.mheshamg.xmovies.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.mheshamg.xmovies.R;
import com.example.mheshamg.xmovies.adapter.MoviesAdapter;
import com.example.mheshamg.xmovies.model.Movie;
import com.example.mheshamg.xmovies.model.MoviesResponse;
import com.example.mheshamg.xmovies.presenter.MainActivityPresenter;
import com.example.mheshamg.xmovies.rest.ApiClient;
import com.example.mheshamg.xmovies.rest.ApiInterface;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.MainActivityViewInterface {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainActivityPresenter mainActivityPresenter;


    private RecyclerView recyclerView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mainActivityPresenter=new MainActivityPresenter(this,this);

        if(!mainActivityPresenter.initialize())
            return;


        recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainActivityPresenter.getMoviesAdapter());

        mainActivityPresenter.fetchTopRatedMovies();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityPresenter.disposeMoviesObserver();
    }

    @Override
    public void updateRecyclerView() {

    }
}
