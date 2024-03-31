package com.cs4520.assignment5

import android.app.Application
import com.cs4520.assignment5.data.AppContainer
import com.cs4520.assignment5.data.AppDataContainer

class MainApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}