package com.example.retrofitgithubuser.di.module;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserModule {

    private static Retrofit retrofit;
    private static final String BASE_URL  = "https://api.github.com";

    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
