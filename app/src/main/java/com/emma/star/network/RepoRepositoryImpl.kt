package com.emma.star.network

import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(private val githubService: GithubService) :
    RepoRepository {

    override suspend fun getRepos(organization: String) = githubService.getRepos(organization)
}