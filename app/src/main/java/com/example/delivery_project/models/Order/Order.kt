package com.example.delivery_project.models.Order

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("restaurant_id") val restaurantId: Int,
    @SerializedName("total") val total: Double,
    @SerializedName("address") val address: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("details") val details: List<OrderDetailResponse>

)

