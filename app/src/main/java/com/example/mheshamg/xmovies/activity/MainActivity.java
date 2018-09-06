
package com.example.mheshamg.xmovies.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mheshamg.xmovies.R;

import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.fagments.FragmentDrawer;
import com.example.mheshamg.xmovies.fagments.TopRatedFragment;
import com.example.mheshamg.xmovies.presenter.MainActivityPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;

import static com.example.mheshamg.xmovies.fagments.FragmentsNames.TOP_RATED_FRAGMENT;


public class MainActivity extends AppCompatActivity implements MainActivityPresenter.MainActivityViewInterface,FragmentDrawer.FragmentDrawerListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainActivityPresenter mainActivityPresenter;




    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        mainActivityPresenter.getFragment(TOP_RATED_FRAGMENT);

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

        if (baseFragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, baseFragment);
            fragmentTransaction.commit();

            // set the toolbar title
            //getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }
}

