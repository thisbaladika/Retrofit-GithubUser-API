package com.example.retrofitgithubuser.component;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitgithubuser.DetailActivity;
import com.example.retrofitgithubuser.FollowActivity;
import com.example.retrofitgithubuser.R;
import com.example.retrofitgithubuser.response.follow.Follower;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.MyViewHolder>{

    private List<Follower> followerList;
    private Context context;

    public FollowerAdapter(List<Follower> followerList, Context context) {
        this.followerList = followerList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_title;
        ImageView img_avatar;
        RelativeLayout itemLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.flw_uname);
            img_avatar = itemView.findViewById(R.id.flw_img);
            itemLayout = itemView.findViewById(R.id.item_flw_layout);
        }
    }


    @NonNull
    @Override
    public FollowerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follow_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerAdapter.MyViewHolder holder, int position) {
        final Follower follower = followerList.get(position);
        holder.txt_title.setText(follower.getLogin());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
            builder.build().load(follower.getAvatar_url())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.img_avatar);

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context.getApplicationContext(), ""+follower.getLogin(), Toast.LENGTH_SHORT).show();
                String uname =  String.valueOf(follower.getLogin());
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("uname", uname);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return followerList.size();
    }


}
