package com.example.randonuserapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.randonuserapp.Adapter.MainScreenAdapter;
import com.example.randonuserapp.Interface.RandomUserApi;
import com.example.randonuserapp.model.RandomUserResults;
import com.example.randonuserapp.model.Result;
import com.example.randonuserapp.service.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MainScreenAdapter adapter;

    private List<Result> resultsList = new ArrayList<>();
    private static Context appContext;
    public static Context getContext() {
        return appContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appContext = this;

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        loadUsers();

    }
    private void loadUsers() {
        Retrofit retrofit = ApiClient.getApiClient();
        RandomUserApi randomUserApi = retrofit.create(RandomUserApi.class);
        Call<RandomUserResults> call = randomUserApi.getData("15");
        call.enqueue(new Callback<RandomUserResults>() {
            @Override
            public void onResponse(Call<RandomUserResults> call, Response<RandomUserResults> response) {
                resultsList = response.body().getResults();
                adapter = new MainScreenAdapter(resultsList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RandomUserResults> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        loadUsers();
        swipeRefreshLayout.setRefreshing(false);
    }

}
