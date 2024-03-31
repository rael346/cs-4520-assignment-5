package com.cs4520.assignment5.ui.productlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs4520.assignment5.data.ProductsRepository
import com.cs4520.assignment5.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val productsRepository: ProductsRepository
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
    }
    private fun getProducts(page: Int) {
        Log.i("PRODUCT LIST", "getting products")
        viewModelScope.launch {
            try {
                _state.value = State.LOADING
                _products.value = productsRepository.getProductsOnPage(page)
                _state.value = State.SUCCESS
                Log.i("PRODUCT LIST", "success" + _products.value.toString())
            } catch (e: Exception) {
                _state.value = State.FAIL
                errorMessage = e.message
                Log.i("PRODUCT LIST", "failure")
            }
        }
    }

    fun incrementPage() {
        _currentPage.value = currentPage.value + 1
        getProducts(currentPage.value)
    }

    fun decrementPage() {
        _currentPage.value = currentPage.value - 1
        getProducts(currentPage.value)
    }
}

