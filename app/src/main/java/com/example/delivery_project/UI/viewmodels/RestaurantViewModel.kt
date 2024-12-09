package com.example.delivery_project.UI.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.delivery_project.models.Order.Order
import com.example.delivery_project.models.Order.OrderResponse
import com.example.delivery_project.models.Restaurant_Product.Restaurant
import com.example.delivery_project.models.Restaurant_Product.Restaurants
import com.example.delivery_project.repositories.OrderRepository
import com.example.delivery_project.repositories.RestaurantRepository

class RestaurantViewModel :ViewModel() {




    private val orderRepository = OrderRepository


    private val _restaurantList = MutableLiveData<Restaurants>().apply {
        value = arrayListOf()
    }

    val restaurantList: LiveData<Restaurants> = _restaurantList


    private val _restaurantDetails = MutableLiveData<Restaurant>()
    val restaurantDetails: LiveData<Restaurant> = _restaurantDetails



   private val _orderResponse = MutableLiveData<OrderResponse>()
    val orderResponse: LiveData<OrderResponse> get() = _orderResponse

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading



    private val _orders = MutableLiveData<List<OrderResponse>>()
    val orders: LiveData<List<OrderResponse>> get() = _orders




    fun getRestaurantList() {
        RestaurantRepository.getRestaurantsList(
            onSuccess = { _restaurantList.value = it },
            onError = { it.printStackTrace() }
        )
    }

    // Obtener detalles de un restaurante específico
    fun getRestaurantDetails(restaurantId: Int) {
        RestaurantRepository.getRestaurantDetails(
            restaurantId,
            onSuccess = {
                _restaurantDetails.value = it },
            onError = {
                it.printStackTrace() }
        )
    }

    fun createOrder(order: Order) {
        _isLoading.value = true
        orderRepository.sendOrder(
            order,
            onSuccess = { response ->
                _isLoading.value = false
                _orderResponse.value = response
                Log.d("OrderViewModel", "Orden creada con éxito: ${response.id} ${response.total} ${response.status} ${response.restaurant_id} ${response.user_id} ${response.created_at} ")
            },
            onError = { throwable ->
                _isLoading.value = false
                _error.value = throwable.message
                Log.e("OrderViewModel", "Error al crear la orden", throwable)
            }
        )
    }

    fun getOrders() {
        orderRepository.getOrders(
            onSuccess = { orders ->
                // Log para asegurarse de que las órdenes fueron obtenidas
                Log.d("OrderViewModel", "Ordenes obtenidas con éxito")
                orders.forEach {
                    Log.d("OrderViewModel", "Orden: ${it.id} ${it.total} ${it.status} ${it.restaurant_id} ${it.user_id} ${it.created_at} ")
                }

                _orders.postValue(orders)

            },
            onError = { throwable ->
                // En caso de error, logueamos el error
                Log.e("OrderViewModel", "Error al obtener las ordenes", throwable)
            }
        )
    }




}