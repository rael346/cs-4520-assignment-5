package com.cs4520.assignment5.data

import com.cs4520.assignment5.model.Product

interface ProductsRepository {
    suspend fun insertAll(products: List<Product>, page: Int)
    suspend fun getProductsOnPage(page: Int): List<Product>
    suspend fun deleteProductsOnPage(page: Int)
}