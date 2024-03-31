package com.cs4520.assignment5.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import com.cs4520.assignment5.MainApplication
import com.cs4520.assignment5.data.ProductsWorkerFactory
import com.cs4520.assignment5.ui.productlist.ProductListViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val config = Configuration.Builder()
                .setWorkerFactory(ProductsWorkerFactory(application().container.productsRepository))
                .build()

            WorkManager.initialize(
                application().applicationContext,
                config
            )

            ProductListViewModel(
                application().container.productsRepository,
                WorkManager.getInstance(application().applicationContext)
            )
        }
    }
}

fun CreationExtras.application(): MainApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
