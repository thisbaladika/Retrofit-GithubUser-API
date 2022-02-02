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
import com.example.retrofitgithubuser.response.follow.Following;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.MyViewHolder>{

    private List<Following> followingList;
    private Context context;

    public FollowingAdapter(List<Following> followingList, Context context) {
        this.followingList = followingList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_title;
        ImageView img_avatar;
        RelativeLayout itemLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.flwing_img);
            img_avatar = itemView.findViewById(R.id.flwing_img);
            itemLayout = itemView.findViewById(R.id.item_flwing_layout);
        }
    }


    @NonNull
    @Override
    public FollowingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_following_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingAdapter.MyViewHolder holder, int position) {
        final Following following = followingList.get(position);
        holder.txt_title.setText(following.getLogin());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(following.getAvatar_url())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.img_avatar);

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname =  String.valueOf(following.getLogin());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("uname", uname);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return followingList.size();
    }


}
