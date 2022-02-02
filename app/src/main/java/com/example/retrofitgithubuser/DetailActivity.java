package com.example.retrofitgithubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitgithubuser.api.ApiService;
import com.example.retrofitgithubuser.component.RepoAdapter;
import com.example.retrofitgithubuser.di.module.UserModule;
import com.example.retrofitgithubuser.response.userDetail.UserDetail;
import com.example.retrofitgithubuser.response.userDetail.UserRepo;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.dt_follower)
    TextView txt_follower;

    @BindView(R.id.dt_following)
    TextView txt_following;

    @BindView(R.id.dt_repo)
    TextView txt_repo;

    @BindView(R.id.dt_img)
    ImageView img_avatar;

    @BindView(R.id.rvRepo)
    RecyclerView recyclerView;

    String uname;
    private RepoAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        Intent intent = getIntent();
        uname =intent.getStringExtra("uname");

        getSupportActionBar().setTitle(uname);

//        Toast.makeText(getApplicationContext(), ""+uname, Toast.LENGTH_LONG).show();

        getData();
        getRepo();
    }

    private void getData() {
        ApiService service = UserModule.getRetrofit().create(ApiService.class);

        Call<UserDetail> call = service.detailUser(uname);
        call.enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                progressDialog.hide();

                UserDetail userDetail = response.body();
//                Toast.makeText(getApplicationContext(), ""+userDetail.getFollowers(), Toast.LENGTH_LONG).show();
                txt_follower.setText(userDetail.getFollowers());
                txt_following.setText(userDetail.getFollowing());
                txt_repo.setText(userDetail.getPublicRepos());

                Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
                builder.downloader(new OkHttp3Downloader(getApplicationContext()));
                builder.build().load(userDetail.getAvatarUrl())
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(img_avatar);
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
                progressDialog.hide();
                Toast.makeText(DetailActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRepo() {
        ApiService service = UserModule.getRetrofit().create(ApiService.class);
        Call<List<UserRepo>> call = service.getRepo(uname);

        call.enqueue(new Callback<List<UserRepo>>() {
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {
                progressDialog.dismiss();
//                List<UserRepo> userRepo = response.body();

//                Toast.makeText(getApplicationContext(), "ini repo"+uname, Toast.LENGTH_LONG).show();

                getAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailActivity.this, "Repo gagal dimuat...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAdapter(List<UserRepo> userRepoList) {
        RecyclerView recyclerView = findViewById(R.id.rvRepo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RepoAdapter(userRepoList, this);
        recyclerView.setAdapter(adapter);
    }
}