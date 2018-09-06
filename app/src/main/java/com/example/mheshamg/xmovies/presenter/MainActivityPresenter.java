package com.example.mheshamg.xmovies.presenter;

import com.example.mheshamg.xmovies.fagments.BaseFragment;
import com.example.mheshamg.xmovies.fagments.FragmentsNames;
import com.example.mheshamg.xmovies.fagments.TopRatedFragment;


import static com.example.mheshamg.xmovies.fagments.FragmentsNames.TOP_RATED_FRAGMENT;
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

    public void getFragment(Enum<FragmentsNames> fragmentName)
    {
        if(fragmentName== TOP_RATED_FRAGMENT)
            mMainActivityViewInterface.updateView(new TopRatedFragment());
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
