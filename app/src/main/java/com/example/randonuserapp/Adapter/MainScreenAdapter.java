package com.example.randonuserapp.Adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.randonuserapp.DetailActivity;
import com.example.randonuserapp.Interface.ItemClickListener;
import com.example.randonuserapp.MainActivity;
import com.example.randonuserapp.R;
import com.example.randonuserapp.ViewHolder.MainViewHolder;
import com.example.randonuserapp.model.Result;

import java.util.List;

public class MainScreenAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private List<Result> results;
    private ItemClickListener itemClickListener;

    public MainScreenAdapter(List<Result> results) {
        this.results = results;
    }
    private Context getContext() {
        return MainActivity.getContext();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.recycler_items, viewGroup, false);
        return new MainViewHolder(v, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder mainViewHolder, int i) {
        final Result result = results.get(i);
        Glide.with(getContext())
                .load(result.getPicture().getLarge())
                .apply(new RequestOptions().optionalCircleCrop())
                .listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mainViewHolder.userImage);
        mainViewHolder.userText.setText(result.getName().getFirst() + " " + result.getName().getLast());
        mainViewHolder.emailText.setText(result.getEmail());

        mainViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("name", mainViewHolder.userText.getText());
                intent.putExtra("image", result.getPicture().getMedium());
                intent.putExtra("bday", result.getDob().getDate());
                intent.putExtra("email", result.getEmail());
                intent.putExtra("phone", result.getPhone());
                intent.putExtra("address", result.getLocation().getStreet() +", "+ result.getLocation().getCity() +", "+ result.getLocation().getState());
                getContext().startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
