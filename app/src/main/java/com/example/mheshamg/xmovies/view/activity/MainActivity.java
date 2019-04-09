
package com.example.mheshamg.xmovies.view.activity;

import android.animation.Animator;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import com.example.mheshamg.xmovies.R;

import com.example.mheshamg.xmovies.view.fagments.BaseFragment;
import com.example.mheshamg.xmovies.presenter.MainActivityPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;

import com.example.mheshamg.xmovies.view.adapter.ViewPagerAdapter;

import static com.example.mheshamg.xmovies.view.fagments.FragmentsNames.FAVORITES_FRAGMENT;
import static com.example.mheshamg.xmovies.view.fagments.FragmentsNames.POPULAR_FRAGMENT;
import static com.example.mheshamg.xmovies.view.fagments.FragmentsNames.TOP_RATED_FRAGMENT;
import static com.example.mheshamg.xmovies.view.fagments.FragmentsNames.UPCOMING_FRAGMENT;

import android.support.design.widget.TabLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainActivityPresenter mainActivityPresenter;
    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppBarLayout appBarLayout;
    private LinearLayout rootLayout;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout=findViewById(R.id.root_layout);


        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circularAnimationActivity();
        }
        else{
            rootLayout.setVisibility(View.VISIBLE);
        }

        appBarLayout=(AppBarLayout) findViewById(R.id.app_bar_layout);
        //splashScreenLayout= (RelativeLayout) findViewById(R.id.splash_screen);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mainActivityPresenter=new MainActivityPresenter();

        if(!mainActivityPresenter.initialize())
            return;

        Fresco.initialize(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        loadMovies();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(new ComponentName(this,SearchActivity.class)));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.setQuery("",false);
                searchView.clearFocus();
                searchView.setIconified(true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            //noinspection SimplifiableIfStatement
            case R.id.action_search:
                Intent searchActivityIntent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(searchActivityIntent);
                return true;

            case R.id.action_settings:
                return true;

            default:
            return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void loadMovies(){
        if (!isConnectedToNetwork()){
            new AlertDialog.Builder(this)
                    .setTitle("Connection Failed")
                    .setMessage("Please Check Your Network Connection")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            loadMovies();
                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
        }
        else{
            setupViewPager(viewPager);
        }
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(mainActivityPresenter.getFragment(UPCOMING_FRAGMENT), "Up Coming");
        adapter.addFragment(mainActivityPresenter.getFragment(TOP_RATED_FRAGMENT), "Top Rated");
        adapter.addFragment(mainActivityPresenter.getFragment(POPULAR_FRAGMENT), "Popular");
        adapter.addFragment(mainActivityPresenter.getFragment(FAVORITES_FRAGMENT), "Favourite");
        viewPager.setAdapter(adapter);
    }

    private void circularAnimationActivity(){
            rootLayout.setVisibility(View.INVISIBLE);
            ViewTreeObserver viewTreeObserver = rootLayout.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        revealActivity(rootLayout.getWidth()/2, rootLayout.getHeight()/2);
                        rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
    }

    protected void revealActivity(int x, int y) {
            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

            // create the animator for this view (the start radius is zero)
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(500);
            circularReveal.setInterpolator(new AccelerateInterpolator());

            // make the view visible and start the animation
            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
    }

    private boolean isConnectedToNetwork(){
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}

