package com.emma.star

import android.app.Application
import com.emma.star.di.AppComponent
import com.emma.star.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .build()
    }
}

lateinit var component: AppComponent