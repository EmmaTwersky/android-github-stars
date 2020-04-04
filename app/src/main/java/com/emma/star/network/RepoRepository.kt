package com.emma.star.network

import com.emma.star.model.Repo

interface RepoRepository {

    suspend fun getRepos(organization: String) : ArrayList<Repo?>
}