package com.cs4520.assignment5

sealed class Screen(
    val route: String,
) {
    object Login: Screen("login")
    object ProductList: Screen("product_list")
}