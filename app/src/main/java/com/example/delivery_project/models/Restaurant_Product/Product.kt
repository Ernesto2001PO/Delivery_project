package com.example.delivery_project.models.Restaurant_Product




typealias Products = List<Product>

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val restaurant_id: Int,
    val image: String,
    var quantity: Int = 0

) {
    val priceDouble: Double
        get() = price.toDouble()
    var selectedQuantity: Int = 0
        get() = quantity

}
