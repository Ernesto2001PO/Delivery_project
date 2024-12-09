package com.example.delivery_project.repositories

import android.util.Log
import com.example.delivery_project.api.JSONPlaceHolderService
import com.example.delivery_project.models.App
import com.example.delivery_project.models.Order.Order
import com.example.delivery_project.models.Order.OrderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object OrderRepository {
    private val retrofit by lazy {
        RetrofitRepository.getRetrofitInstance(App.instance.applicationContext)
    }
    private val service by lazy {
        retrofit.create(JSONPlaceHolderService::class.java)
    }

    fun sendOrder(
        order: Order,
        onSuccess: (OrderResponse) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.createOrder(order).enqueue(object : Callback<OrderResponse> {
            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                if (response.isSuccessful) {
                    val orderResponse = response.body()
                    if (orderResponse != null) {
                        Log.d("OrderRepository", "Orden creada con éxito: ${orderResponse.id}")
                        onSuccess(orderResponse)
                    } else {
                        Log.e("OrderRepository", "Error: Respuesta nula")
                        onError(Throwable("La respuesta está vacía"))
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Error desconocido"
                    Log.e("OrderRepository", "Error en la respuesta: $errorMessage")
                    onError(Throwable("Error en la respuesta del servidor: $errorMessage"))
                }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                Log.e("OrderRepository", "Error en la llamada: ${t.message}")
                onError(t)
            }
        })

    }


    fun getOrders(
        onSuccess: (List<OrderResponse>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        service.getOrders().enqueue(object : Callback<List<OrderResponse>> {
            override fun onResponse(
                call: Call<List<OrderResponse>>,
                response: Response<List<OrderResponse>>
            ) {
                if (response.isSuccessful) {
                    val orders = response.body()
                    if (orders != null) {
                        Log.d("OrderRepository", "Ordenes obtenidas con éxito")
                        onSuccess(orders)
                    } else {
                        Log.e("OrderRepository", "Error: Respuesta nula")
                        onError(Throwable("La respuesta está vacía"))
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Error desconocido"
                    Log.e("OrderRepository", "Error en la respuesta: $errorMessage")
                    onError(Throwable("Error en la respuesta del servidor: $errorMessage"))
                }
            }

            override fun onFailure(call: Call<List<OrderResponse>>, t: Throwable) {
                Log.e("OrderRepository", "Error en la llamada: ${t.message}")
                onError(t)
            }
        })
    }



}