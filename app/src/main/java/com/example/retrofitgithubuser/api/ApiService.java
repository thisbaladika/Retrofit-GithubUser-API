package com.example.retrofitgithubuser.api;

import com.example.retrofitgithubuser.response.follow.Follower;
import com.example.retrofitgithubuser.response.follow.Following;
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
    Call<UserDetail> detailUser(@Header("Authorization") String authorization,
                                @Path("username") String username);

    @GET("users/{username}/repos")
    Call<List<UserRepo>> getRepo(@Header("Authorization") String authorization,
                                @Path("username") String username);

    @GET("users/{username}/followers")
    Call<List<Follower>> getFollower(@Header("Authorization") String authorization,
                                @Path("username") String username);

    @GET("users/{username}/following")
    Call<List<Following>> getFollowing(@Header("Authorization") String authorization,
                                    @Path("username") String username);
}
