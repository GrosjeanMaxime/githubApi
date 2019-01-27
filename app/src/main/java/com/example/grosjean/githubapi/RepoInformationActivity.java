package com.example.grosjean.githubapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.grosjean.githubapi.rest.models.Branches;
import com.example.grosjean.githubapi.rest.models.Item;
import com.example.grosjean.githubapi.rest.models.Repo;
import com.example.grosjean.githubapi.rest.services.GithubServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepoInformationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Branches> mItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_information);

        mItemList = new ArrayList();
        mRecyclerView = findViewById(R.id.my_branches_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRepoBranchesAdapter(mItemList);
        mRecyclerView.setAdapter(mAdapter);

        Intent intent = getIntent();
        String[] user_repo = intent.getStringExtra("user_repo").split("/");

        branchesList(user_repo);
    }


    private void branchesList(String[] user_repo) {
        GithubServices githubService = new Retrofit.Builder()
                .baseUrl(GithubServices.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubServices.class);

        githubService.branchesRepo(user_repo[0], user_repo[1]).enqueue(new Callback<List<Branches>>() {

            @Override
            public void onResponse(Call<List<Branches>> call, Response<List<Branches>> response) {
                if (response.body() != null) {
                    mItemList.addAll(response.body());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Branches>> call, Throwable t) {
                Log.d("MyApp", t.getMessage());
            }
        });

    }
}
