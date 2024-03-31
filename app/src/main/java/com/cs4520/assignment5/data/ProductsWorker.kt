package com.cs4520.assignment5.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cs4520.assignment5.ui.productlist.ProductListViewModel

class ProductsWorker(
    private val appContext: Context,
    private val params: WorkerParameters,
    private val productsRepository: ProductsRepository,
): CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        return try {
            val page = params.inputData.getInt("page", 1);
            productsRepository.getProductsOnPage(page)
            Result.success()
        } catch (e: Exception) {
            e.message?.let { Log.e("Worker error", it) }
            Result.failure()
        }
    }
}