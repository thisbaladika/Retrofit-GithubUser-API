package com.example.retrofitgithubuser.api;

import com.example.retrofitgithubuser.response.userDetail.UserDetail;
import com.example.retrofitgithubuser.response.userDetail.UserRepo;
import com.example.retrofitgithubuser.response.userSearch.UserSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/search/users")
    Call<UserSearch> searchUser(@Header("Authorization") String authorization,
                                @Query("q") String username);

    @GET("users/{username}")
    Call<UserDetail> detailUser(@Path("username") String username);

    @GET("users/{username}/repos")
    Call<List<UserRepo>> getRepo(@Path("username") String username);
}
