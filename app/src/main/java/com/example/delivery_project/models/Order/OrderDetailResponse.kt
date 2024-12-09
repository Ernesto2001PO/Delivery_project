package com.example.delivery_project.models.Order


import com.example.delivery_project.models.Restaurant_Product.Product

data class OrderDetailResponse(
    val product_id: Int,
    val qty: Int,
    val price: String,
    val product: Product,
)


