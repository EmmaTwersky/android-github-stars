package com.emma.star.network

import com.emma.star.model.Repo


interface RepoRepository {

    fun getRepos(organization: String,
                 onSuccess: (repos: ArrayList<Repo?>) -> Unit,
                 onFailure: (t: Throwable) -> Unit)
}