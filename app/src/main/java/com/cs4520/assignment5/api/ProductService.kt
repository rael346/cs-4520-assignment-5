package com.cs4520.assignment5.api

import com.cs4520.assignment5.model.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("random")
    suspend fun getProducts(@Query("page") pageNum: Int): Response<List<Product>>
}