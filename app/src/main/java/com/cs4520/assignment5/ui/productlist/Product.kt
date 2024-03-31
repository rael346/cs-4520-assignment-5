package com.cs4520.assignment5.ui.productlist

data class Product (
    val name: String,
    val type: String,
    val expiryDate: String?,
    val price: Double,
) {
//    fun toEntity(page: Int): ProductEntity {
//        return ProductEntity(
//            name = name,
//            type = type,
//            expiryDate = expiryDate,
//            price = price,
//            page = page
//        )
//    }
}
