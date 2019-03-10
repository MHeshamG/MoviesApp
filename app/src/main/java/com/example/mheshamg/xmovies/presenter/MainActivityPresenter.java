package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.fagments.FavouritesFragment;
import com.example.mheshamg.xmovies.fagments.FragmentsNames;
import com.example.mheshamg.xmovies.fagments.PopularFragment;
import com.example.mheshamg.xmovies.fagments.TopRatedFragment;
import com.example.mheshamg.xmovies.fagments.UpComingFragment;


import static com.example.mheshamg.xmovies.fagments.FragmentsNames.FAVORITES_FRAGMENT;
import static com.example.mheshamg.xmovies.fagments.FragmentsNames.POPULAR_FRAGMENT;
import static com.example.mheshamg.xmovies.fagments.FragmentsNames.TOP_RATED_FRAGMENT;
import static com.example.mheshamg.xmovies.fagments.FragmentsNames.UPCOMING_FRAGMENT;
import static com.example.mheshamg.xmovies.rest.Constants.API_KEY;

public class MainActivityPresenter
{

    private final static  String TAG=MainActivityPresenter.class.getSimpleName();


    private MainActivityViewInterface mMainActivityViewInterface;

    public MainActivityPresenter(MainActivityViewInterface mainActivityViewInterface)
    {
        this.mMainActivityViewInterface=mainActivityViewInterface;
    }

    public boolean initialize()
    {
        if (API_KEY.isEmpty()) {
            return false;
        }
        //Fresco.initialize(mContext);

        //moviesAdapter=new MoviesAdapter(moviesList, R.layout.list_item_movie,mContext,null);


        return true;
    }

    public BaseFragment getFragment(Enum<FragmentsNames> fragmentName)
    {
        BaseFragment baseFragment=null;

        if(fragmentName== TOP_RATED_FRAGMENT){
            baseFragment=new TopRatedFragment();
            mMainActivityViewInterface.updateView(baseFragment);
        }
        else if(fragmentName== UPCOMING_FRAGMENT){
            baseFragment=new UpComingFragment();
            mMainActivityViewInterface.updateView(baseFragment);
        }
        else if(fragmentName== POPULAR_FRAGMENT){
            baseFragment=new PopularFragment();
            mMainActivityViewInterface.updateView(baseFragment);
        }
        else if(fragmentName== FAVORITES_FRAGMENT){
            baseFragment=new FavouritesFragment();
            mMainActivityViewInterface.updateView(baseFragment);
        }
        return baseFragment;

    }

    /**
     *
     *
     */
    public interface MainActivityViewInterface
    {
        public void updateView (BaseFragment baseFragment);
    }
}
