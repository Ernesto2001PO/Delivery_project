package com.example.delivery_project.UI.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delivery_project.UI.adapters.RestaurantAdapter
import com.example.delivery_project.UI.viewmodels.RestaurantViewModel
import com.example.delivery_project.databinding.MenuRestaurantBinding
import com.example.delivery_project.interfaces.OnRestaurantClickListener
import com.example.delivery_project.models.Restaurant_Product.Restaurant

class Menu_RestaurantActivity : AppCompatActivity(), OnRestaurantClickListener {

    private lateinit var binding: MenuRestaurantBinding
    private val viewModel: RestaurantViewModel by viewModels()
    private lateinit var adapter: RestaurantAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MenuRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()


        setupViewModelObservers()

        viewModel.getRestaurantList()



    }

    private fun setupRecyclerView() {
        adapter = RestaurantAdapter(emptyList(), emptyList(), this)
        binding.rvRestaurant.adapter = adapter
        binding.rvRestaurant.layoutManager = LinearLayoutManager(this)
    }



    private fun setupViewModelObservers() {
       viewModel.restaurantList.observe(this) { restaurants ->
           Log.d("Menu_RestaurantActivity", "Restaurantes: $restaurants")
           adapter =  RestaurantAdapter(restaurants, emptyList(), this)
           binding.rvRestaurant.adapter = adapter

            adapter.notifyDataSetChanged()
        }
    }

    override fun onRestaurantClick(restaurant: Restaurant) {
        Log.d("Menu_RestaurantActivity", "restaurantId: ${restaurant.id}, address: ${restaurant.address}, latitude: ${restaurant.latitude}, longitude: ${restaurant.longitude}")

        val intent = Intent(this, RestaurantDetailActivity::class.java)
        intent.putExtra("restaurant_id", restaurant.id)
        intent.putExtra("latitude", restaurant.latitude)
        intent.putExtra("longitude", restaurant.longitude)

        Log.d("AddOrderActivity", "latitude (envio): ${restaurant.latitude}, longitude (envio): ${restaurant.longitude}")
        startActivity(intent)


    }



}