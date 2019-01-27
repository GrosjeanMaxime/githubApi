package com.example.grosjean.githubapi.rest.services;

import com.example.grosjean.githubapi.rest.models.Branches;
import com.example.grosjean.githubapi.rest.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubServices {

    public static final String ENDPOINT = "https://api.github.com";

    @GET("/search/repositories")
    Call<Repo> searchRepos(@Query("q") String q);

    @GET("/repos/{user}/{repo}/branches")
    Call<List<Branches>> branchesRepo(@Path("user") String user,@Path("repo") String repo);
}