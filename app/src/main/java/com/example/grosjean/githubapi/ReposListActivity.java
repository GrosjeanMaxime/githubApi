package com.example.grosjean.githubapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

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

public class ReposListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText editText;
    private List<Item> mItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_list);
        mItemList = new ArrayList();
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyReposListAdapter(mItemList);
        mRecyclerView.setAdapter(mAdapter);
        editText = (EditText) findViewById(R.id.editText);
        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (v.getText().toString().length() != 0) {
                        mItemList.clear();
                        mRecyclerView.setAdapter(mAdapter);
                        fillList(v.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void fillList(String keyWords) {
        GithubServices githubService = new Retrofit.Builder()
                .baseUrl(GithubServices.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubServices.class);

        githubService.searchRepos(keyWords).enqueue(new Callback<Repo> () {

            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                if (response.body() != null) {
                    mItemList.addAll(response.body().getItems());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Log.d("MyApp", t.getMessage());
            }
        });

    }

}
