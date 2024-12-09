package com.example.delivery_project.UI.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delivery_project.UI.adapters.ProductsAdapter
import com.example.delivery_project.UI.viewmodels.RestaurantViewModel
import com.example.delivery_project.databinding.MenuProductsBinding
import com.example.delivery_project.models.Order.Order
import com.example.delivery_project.models.Order.OrderDetailResponse

class RestaurantDetailActivity : AppCompatActivity() {

    private val restaurantViewModel: RestaurantViewModel by viewModels()
    private lateinit var binding: MenuProductsBinding
    private lateinit var adapter: ProductsAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        val restaurantId = intent.getIntExtra("restaurant_id", 0)

        if (restaurantId != 0) {
            restaurantViewModel.getRestaurantDetails(restaurantId)
        }

        restaurantViewModel.restaurantDetails.observe(this, Observer { restaurant ->
            restaurant?.let {
                adapter.updateProducts(it.products)
            }

        })

        binding.btnAddOrder.setOnClickListener {
            val restaurantId = intent.getIntExtra("restaurant_id", 0)
            val latitude = intent.getStringExtra("latitude")
            val longitude = intent.getStringExtra("longitude")

            val address = intent.getStringExtra("address") ?: "Sin direccion"



            val productDetails = adapter.getSelectedProducts().map { product ->
                product.selectedQuantity = product.quantity
                OrderDetailResponse(
                    product_id = product.id,
                    qty = product.selectedQuantity,
                    price = product.price,
                    product = product
                )
            }

            if (latitude == null || longitude == null) {
                Log.e("AddOrderActivity", "La latitud o longitud están vacías")
            } else {
                val order = Order(
                    restaurantId = restaurantId,
                    total = productDetails.sumOf { it.qty * it.price.toDouble() },
                    address = address,
                    latitude = latitude,
                    longitude = longitude,
                    details = productDetails
                )
                restaurantViewModel.createOrder(order)
            }

        }

        binding.btnMyOrders.setOnClickListener {
            intent = Intent(this, OrdersActivity::class.java)
            intent.putExtra("restaurant_id", restaurantId)
            intent.putExtra("latitude", intent.getStringExtra("latitude"))
            intent.putExtra("longitude", intent.getStringExtra("longitude"))

            Log.d("RestaurantDetailActivity", "restaurantId: $restaurantId, latitude: ${intent.getStringExtra("latitude")}, longitude: ${intent.getStringExtra("longitude")}")
            startActivity(intent)


        }
    }


    private fun setupRecyclerView() {
        adapter = ProductsAdapter(emptyList())
        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
    }


}
