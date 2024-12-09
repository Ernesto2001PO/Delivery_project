package com.example.delivery_project.repositories

import android.util.Log
import com.example.delivery_project.api.JSONPlaceHolderService
import com.example.delivery_project.models.App
import com.example.delivery_project.models.Restaurant_Product.Restaurant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RestaurantRepository {

    private val retrofit by lazy {
        RetrofitRepository.getRetrofitInstance(App.instance.applicationContext)
    }
    private val service by lazy {
        retrofit.create(JSONPlaceHolderService::class.java)
    }

    fun getRestaurantsList(
        onSuccess: (List<Restaurant>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getRestaurants().enqueue(object : Callback<List<Restaurant>> {
            override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                if (response.isSuccessful) {
                    val restaurants = response.body()
                    if (restaurants != null) {
                        Log.d("RestaurantRepository", "Datos recibidos: ${restaurants.size}")
                        onSuccess(restaurants)
                    } else {
                        Log.e("RestaurantRepository", "Error: Lista de restaurantes nula")
                        onError(Throwable("La lista de restaurantes está vacía"))
                    }
                } else {
                    Log.e("RestaurantRepository", "Error: ${response.errorBody()}")
                    onError(Throwable("Error en la respuesta del servidor"))
                }
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                Log.e("RestaurantRepository", "Error en la llamada: ${t.message}")
                onError(t)
            }
        })
    }

    fun getRestaurantDetails(
        restaurantId: Int,
        onSuccess: (Restaurant) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getRestaurantDetails(restaurantId).enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                if (response.isSuccessful) {
                    val restaurant = response.body()
                    if (restaurant != null) {
                        Log.d("RestaurantRepository", "Detalles del restaurante recibidos")
                        onSuccess(restaurant)
                    } else {
                        Log.e("RestaurantRepository", "Error: Restaurante nulo")
                        onError(Throwable("No se encontraron detalles del restaurante"))
                    }
                } else {
                    Log.e("RestaurantRepository", "Error: ${response.errorBody()}")
                    onError(Throwable("Error en la respuesta del servidor"))
                }
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                Log.e("RestaurantRepository", "Error en la llamada: ${t.message}")
                onError(t)
            }
        })
    }









}



