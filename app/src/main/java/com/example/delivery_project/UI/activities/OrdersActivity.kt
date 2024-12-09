package com.example.delivery_project.UI.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delivery_project.UI.adapters.OrderAdapter
import com.example.delivery_project.UI.viewmodels.RestaurantViewModel
import com.example.delivery_project.databinding.MenuOrdersBinding


class OrdersActivity : AppCompatActivity() {
    private lateinit var binding: MenuOrdersBinding
    private lateinit var adapterOrders: OrderAdapter
    private val restaurantViewModel: RestaurantViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerViewOrders()


        restaurantViewModel.getOrders()

        restaurantViewModel.orders.observe(this, { orders ->
            Log.d("OrdersActivity", "Ordenes recibidas: $orders")
            adapterOrders.updateOrders(orders)
        })

    }

    private fun setupRecyclerViewOrders() {
        adapterOrders = OrderAdapter(emptyList())
        binding.rvOrders.adapter = adapterOrders
        binding.rvOrders.layoutManager = LinearLayoutManager(this)

    }




}