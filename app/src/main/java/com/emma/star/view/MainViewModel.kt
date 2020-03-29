package com.emma.star.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emma.star.model.Repo
import com.emma.star.network.RepoRepository


class MainViewModel(private val repoRepository: RepoRepository) : ViewModel() {

    private val _repos = MutableLiveData<ArrayList<Repo?>>()
    val repos: LiveData<ArrayList<Repo?>>
        get() = _repos

    private val _searchStatus = MutableLiveData<Boolean>()
    val searchStatus: LiveData<Boolean>
        get() = _searchStatus

    fun searchRepos(organization: String) {
        _searchStatus.value = true
        repoRepository.getRepos(
            organization,
            { repos ->
                repos.sortByDescending { it?.stars}
                _repos.value = repos
                _searchStatus.value = false
            },
            { t ->
                Log.e("MainActivity", "onFailure: ", t)
                _searchStatus.value = false
            }
        )
    }
}