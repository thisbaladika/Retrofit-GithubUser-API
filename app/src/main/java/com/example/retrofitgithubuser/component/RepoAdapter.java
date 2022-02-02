package com.example.retrofitgithubuser.component;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitgithubuser.DetailActivity;
import com.example.retrofitgithubuser.R;
import com.example.retrofitgithubuser.response.userDetail.UserRepo;
import com.example.retrofitgithubuser.response.userSearch.UserSearchData;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.MyViewHolder>{

    private List<UserRepo> userRepoList;
    private Context context;

    public RepoAdapter(List<UserRepo> userRepoList, Context context) {
        this.userRepoList = userRepoList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_title;
        TextView txt_lang;
        RelativeLayout itemLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.repo_title);
            txt_lang = itemView.findViewById(R.id.repo_lang);
            itemLayout = itemView.findViewById(R.id.repo_layout);
        }
    }


    @NonNull
    @Override
    public RepoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoAdapter.MyViewHolder holder, int position) {
        final UserRepo userRepo = userRepoList.get(position);
        holder.txt_title.setText(userRepo.getName());
        holder.txt_lang.setText(userRepo.getLanguage());


//        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return userRepoList.size();
    }


}
