package com.example.delivery_project.models.Restaurant_Product



typealias Restaurants = List<Restaurant>

data class Restaurant(
    val id: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val logo: String,
    val products: List<Product>

)

