package com.cs4520.assignment5.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.cs4520.assignment5.data.ProductsRepository
import com.cs4520.assignment5.data.ProductsWorker
import com.cs4520.assignment5.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ProductListViewModel(
    private val productsRepository: ProductsRepository,
    private val workManager: WorkManager
): ViewModel() {
    enum class State {
        LOADING, SUCCESS, FAIL
    }

    private val _state = MutableStateFlow<State>(State.LOADING)
    val state = _state.asStateFlow()
    var errorMessage: String? = null

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _currentPage = MutableStateFlow<Int>(1)
    val currentPage = _currentPage.asStateFlow()

    init {
        getProducts(1)
        scheduleProducts(1)
    }
    private fun getProducts(page: Int) {
        viewModelScope.launch {
            try {
                _state.value = State.LOADING
                _products.value = productsRepository.getProductsOnPage(page)
                _state.value = State.SUCCESS
            } catch (e: Exception) {
                _state.value = State.FAIL
                errorMessage = e.message
            }
        }
    }

    private fun scheduleProducts(page: Int) {
        val data = workDataOf("page" to page)
        val work = PeriodicWorkRequestBuilder<ProductsWorker>(1, TimeUnit.HOURS)
            .setInputData(data)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "Get Products",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            work
        )
    }

    fun incrementPage() {
        _currentPage.value = currentPage.value + 1
        getProducts(_currentPage.value)
        scheduleProducts(_currentPage.value)
    }

    fun decrementPage() {
        _currentPage.value = currentPage.value - 1
        getProducts(_currentPage.value)
        scheduleProducts(_currentPage.value)
    }
}

