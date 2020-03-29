package com.emma.star.di

import com.emma.star.network.RepoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun providesMainViewModelFactory(repoRepository: RepoRepository): ViewModelFactory {
        return ViewModelFactory(repoRepository)
    }
}