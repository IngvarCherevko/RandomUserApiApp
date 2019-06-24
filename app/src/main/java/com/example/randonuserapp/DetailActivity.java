package com.example.randonuserapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import static com.example.randonuserapp.MainActivity.getContext;

public class DetailActivity extends AppCompatActivity {
    
    TextView userNameDetail, userBDayDetail, userEmailDetail, userPhoneDetail, userAddressDetail;
    ImageView userImageDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initValue();
        loadDetail();
    }

    private void initValue() {
        userNameDetail = findViewById(R.id.user_name_detail);
        userBDayDetail = findViewById(R.id.user_bday_detail);
        userEmailDetail = findViewById(R.id.user_email_detail);
        userPhoneDetail = findViewById(R.id.user_phone_detail);
        userAddressDetail = findViewById(R.id.user_address_detail);
        userImageDetail = findViewById(R.id.user_image_detail);
    }

    private void loadDetail() {
        Intent intent = getIntent();
        userNameDetail.setText(intent.getStringExtra("name"));
        userBDayDetail.setText(intent.getStringExtra("bday"));
        userEmailDetail.setText(intent.getStringExtra("email"));
        userPhoneDetail.setText(intent.getStringExtra("phone"));
        userAddressDetail.setText(intent.getStringExtra("address"));
        Glide.with(getContext())
                .load(intent.getStringExtra("image"))
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
                .into(userImageDetail);
    }
}
