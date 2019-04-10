package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.view.fagments.BaseFragment;
import com.example.mheshamg.xmovies.view.fagments.FavouritesFragment;
import com.example.mheshamg.xmovies.view.fagments.FragmentsNames;
import com.example.mheshamg.xmovies.view.fagments.PopularFragment;
import com.example.mheshamg.xmovies.view.fagments.TopRatedFragment;
import com.example.mheshamg.xmovies.view.fagments.UpComingFragment;


import static com.example.mheshamg.xmovies.view.fagments.FragmentsNames.FAVORITES_FRAGMENT;
import static com.example.mheshamg.xmovies.view.fagments.FragmentsNames.POPULAR_FRAGMENT;
import static com.example.mheshamg.xmovies.view.fagments.FragmentsNames.TOP_RATED_FRAGMENT;
import static com.example.mheshamg.xmovies.view.fagments.FragmentsNames.UPCOMING_FRAGMENT;
import static com.example.mheshamg.xmovies.frameworks.rest.Constants.API_KEY;

public class MainActivityPresenter
{

    private final static  String TAG=MainActivityPresenter.class.getSimpleName();



    public MainActivityPresenter()
    {
    }

    public boolean initialize()
    {
        if (API_KEY.isEmpty()) {
            return false;
        }
        return true;
    }

    public BaseFragment getFragment(Enum<FragmentsNames> fragmentName)
    {
        BaseFragment baseFragment=null;

        if(fragmentName== TOP_RATED_FRAGMENT){
            baseFragment=new TopRatedFragment();
        }
        else if(fragmentName== UPCOMING_FRAGMENT){
            baseFragment=new UpComingFragment();
        }
        else if(fragmentName== POPULAR_FRAGMENT){
            baseFragment=new PopularFragment();
        }
        else if(fragmentName== FAVORITES_FRAGMENT){
            baseFragment=new FavouritesFragment();

        }
        return baseFragment;

    }


}
