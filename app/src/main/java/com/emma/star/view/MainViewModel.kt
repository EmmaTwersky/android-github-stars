package com.emma.star.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emma.star.model.Repo
import com.emma.star.network.RepoRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repoRepository: RepoRepository) : ViewModel() {

    private val _repos = MutableLiveData<ArrayList<Repo?>>()
    val repos: LiveData<ArrayList<Repo?>>
        get() = _repos

    fun searchRepos(organization: String) {
        viewModelScope.launch {
            try {
                _repos.value?.clear()
                val searchResults = repoRepository.getRepos(organization)
                handleReposSorting(searchResults)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Exception: ", e)
            }
        }
    }

    private fun handleReposSorting(searchResults: ArrayList<Repo?>) {
        searchResults.sortByDescending { it?.stars }
        _repos.value = searchResults
    }
}