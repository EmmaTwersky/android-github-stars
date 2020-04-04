package com.emma.star.network

import com.emma.star.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/orgs/{org}/repos")
    suspend fun getRepos(@Path("org") organization: String): ArrayList<Repo?>
}