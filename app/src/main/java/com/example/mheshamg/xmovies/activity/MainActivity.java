
package com.example.mheshamg.xmovies.activity;

import android.animation.Animator;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mheshamg.xmovies.R;

import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.fagments.FragmentDrawer;
import com.example.mheshamg.xmovies.fagments.TopRatedFragment;
import com.example.mheshamg.xmovies.presenter.MainActivityPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;

import com.example.mheshamg.xmovies.adapter.ViewPagerAdapter;

import static com.example.mheshamg.xmovies.fagments.FragmentsNames.POPULAR_FRAGMENT;
import static com.example.mheshamg.xmovies.fagments.FragmentsNames.TOP_RATED_FRAGMENT;
import static com.example.mheshamg.xmovies.fagments.FragmentsNames.UPCOMING_FRAGMENT;

import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MainActivityPresenter.MainActivityViewInterface,FragmentDrawer.FragmentDrawerListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainActivityPresenter mainActivityPresenter;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AppBarLayout appBarLayout;
    private RelativeLayout splashScreenLayout;
    private DrawerLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout=findViewById(R.id.drawer_layout);



        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
        } else {
            rootLayout.setVisibility(View.VISIBLE);
        }


        appBarLayout=(AppBarLayout) findViewById(R.id.app_bar_layout);
        splashScreenLayout= (RelativeLayout) findViewById(R.id.splash_screen);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        mainActivityPresenter=new MainActivityPresenter(this);

        if(!mainActivityPresenter.initialize())
            return;

        Fresco.initialize(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
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

    @Override
    public void updateView(BaseFragment baseFragment) {

    }

    @Override
    public void proceedSplashScreen() {
        /*splashScreenLayout.setVisibility(View.GONE);
        appBarLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);*/
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(mainActivityPresenter.getFragment(UPCOMING_FRAGMENT), "Up Coming");
        adapter.addFragment(mainActivityPresenter.getFragment(TOP_RATED_FRAGMENT), "Top Rated");
        adapter.addFragment(mainActivityPresenter.getFragment(POPULAR_FRAGMENT), "Popular");
        viewPager.setAdapter(adapter);
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

}

