package com.cs4520.assignment5.data

import android.content.Context

interface AppContainer {
    val productsRepository: ProductsRepository
}
class AppDataContainer(private val context: Context): AppContainer {
    override val productsRepository: ProductsRepository by lazy {
        OfflineProductsRepository(OfflineDatabase.getDatabase(context).productDao(), context)
    }
}
