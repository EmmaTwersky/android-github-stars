package com.emma.star.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emma.star.view.MainViewModel
import com.emma.star.network.RepoRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val repoRepository: RepoRepository) : ViewModelProvider.Factory {

    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}