package com.emma.star.network

import com.emma.star.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class RepoRepositoryImpl @Inject constructor(private val githubService: GithubService) :
    RepoRepository {

    override fun getRepos(organization: String, onSuccess: (repos: ArrayList<Repo?>) -> Unit, onFailure: (t: Throwable) -> Unit) {
        githubService.getRepos(organization).enqueue(object : Callback<ArrayList<Repo?>> {
            override fun onResponse(call: Call<ArrayList<Repo?>>, response: Response<ArrayList<Repo?>>) {
                response.body()?.let { repos ->
                    onSuccess.invoke(repos)
                }
            }

            override fun onFailure(call: Call<ArrayList<Repo?>>, t: Throwable) {
                onFailure.invoke(t)
            }
        })
    }
}