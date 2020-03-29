package com.emma.star.di

import com.emma.star.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(activity: MainActivity)
}