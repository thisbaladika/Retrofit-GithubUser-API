package com.example.retrofitgithubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofitgithubuser.api.ApiService;
import com.example.retrofitgithubuser.component.FollowerAdapter;
import com.example.retrofitgithubuser.component.FollowingAdapter;
import com.example.retrofitgithubuser.component.RepoAdapter;
import com.example.retrofitgithubuser.di.module.UserModule;
import com.example.retrofitgithubuser.response.follow.Follower;
import com.example.retrofitgithubuser.response.follow.Following;
import com.example.retrofitgithubuser.response.userDetail.UserDetail;
import com.example.retrofitgithubuser.response.userDetail.UserRepo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowActivity extends AppCompatActivity {

    @BindView(R.id.rvFlw)
    RecyclerView recyclerView;

    private String value;
    private String uname;
    List<Follower> followerList;
    private FollowerAdapter adapterFollower;
    private FollowingAdapter adapterFollowing;
    private ProgressDialog progressDialog;
    private String ApiKey = "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";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(FollowActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        Intent intent = getIntent();
        value =intent.getStringExtra("value");
        uname =intent.getStringExtra("uname");

        if (value.equalsIgnoreCase("follower")){
            getFollower(uname);
        }else {
            getFollowing(uname);
        }

//        if (value.equalsIgnoreCase("following")){
//            getFollowing(uname);
//        }

//        Toast.makeText(getApplicationContext(), "value "+value, Toast.LENGTH_SHORT).show();


    }

    private void getFollowing(String uname) {
        ApiService service = UserModule.getRetrofit().create(ApiService.class);
        Call<List<Following>> call = service.getFollowing(ApiKey,uname);
        call.enqueue(new Callback<List<Following>>() {
            @Override
            public void onResponse(Call<List<Following>> call, Response<List<Following>> response) {
                progressDialog.dismiss();
                getAdapterFollowing(response.body());
            }

            @Override
            public void onFailure(Call<List<Following>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(FollowActivity.this, "Repo gagal dimuat...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getAdapterFollowing(List<Following> followingList) {
        RecyclerView recyclerView = findViewById(R.id.rvFlw);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterFollowing = new FollowingAdapter(followingList, this);
        recyclerView.setAdapter(adapterFollower);

    }


    private void getFollower(String uname) {
        ApiService service = UserModule.getRetrofit().create(ApiService.class);
        Call<List<Follower>> call = service.getFollower(ApiKey,uname);
        call.enqueue(new Callback<List<Follower>>() {
            @Override
            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                progressDialog.dismiss();
                getAdapterFollower(response.body());
            }

            @Override
            public void onFailure(Call<List<Follower>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(FollowActivity.this, "Repo gagal dimuat...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAdapterFollower(List<Follower> followerList) {
        RecyclerView recyclerView = findViewById(R.id.rvFlw);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterFollower = new FollowerAdapter(followerList, this);
        recyclerView.setAdapter(adapterFollower);
    }
}