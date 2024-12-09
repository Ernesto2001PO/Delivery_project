package com.example.delivery_project.api

import com.example.delivery_project.models.Login.LoginRequest
import com.example.delivery_project.models.Login.LoginResponse
import com.example.delivery_project.models.Restaurant_Product.Restaurant
import com.example.delivery_project.models.Login.User
import com.example.delivery_project.models.Order.Order
import com.example.delivery_project.models.Order.OrderResponse
import com.example.delivery_project.models.Restaurant_Product.Restaurants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


interface JSONPlaceHolderService {

    @POST("users")
    fun createUser( @Body user: User): Call<User>

    @Headers("Accept: application/json")
    @POST("users/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>


    @Headers("Accept: application/json")
    @GET("restaurants")
    fun getRestaurants(): Call<Restaurants>

    @Headers("Accept: application/json")
    @GET("restaurants/{id}")
    fun getRestaurantDetails(@Path("id") restaurantId: Int): Call<Restaurant>


    @Headers("Accept: application/json")
    @POST("orders")
    fun createOrder(@Body order: Order): Call<OrderResponse>


    @Headers("Accept: application/json")
    @GET("orders")
    fun getOrders(): Call<List<OrderResponse>>




}