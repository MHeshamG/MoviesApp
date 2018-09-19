package com.example.mheshamg.xmovies.rest;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL="http://api.themoviedb.org/3/";
    private static Retrofit retrofit=null;
    private static ApiInterface apiInterface=null;

    private static Retrofit getClient() {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getApiInterface()
    {
        if(apiInterface==null) {
            apiInterface= ApiClient.getClient().create(ApiInterface.class);
        }
        return apiInterface;
    }

}
