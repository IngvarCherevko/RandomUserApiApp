package com.example.randonuserapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.randonuserapp.Interface.ItemClickListener;
import com.example.randonuserapp.R;

//import butterknife.BindView;

public class MainViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

    private ItemClickListener itemClickListener;

    //@BindView(R.id.user_image)
    public ImageView userImage;
   // @BindView(R.id.user_name)
    public TextView userText;
    //@BindView(R.id.user_email)
    public TextView emailText;


    public MainViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        userImage = itemView.findViewById(R.id.user_image);
        userText = itemView.findViewById(R.id.user_name);
        emailText = itemView.findViewById(R.id.user_email);

        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onItemClick(view, getAdapterPosition());
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}
