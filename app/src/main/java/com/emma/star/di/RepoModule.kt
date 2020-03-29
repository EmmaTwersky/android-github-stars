package com.emma.star.di

import com.emma.star.network.RepoRepositoryImpl
import com.emma.star.network.RepoRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun providesRepoRepository(repoRepository: RepoRepositoryImpl): RepoRepository
}