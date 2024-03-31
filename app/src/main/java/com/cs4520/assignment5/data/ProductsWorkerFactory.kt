package com.cs4520.assignment5.data

import android.content.Context
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters

class ProductsWorkerFactory( private val productsRepository: ProductsRepository ): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ProductsWorker {
        return ProductsWorker(appContext, workerParameters, productsRepository)
    }
}