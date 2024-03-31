package com.cs4520.assignment5.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.cs4520.assignment5.MainApplication
import com.cs4520.assignment5.ui.productlist.ProductListViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ProductListViewModel(application().container.productsRepository)
        }
    }
}

fun CreationExtras.application(): MainApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MainApplication)
