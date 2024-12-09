package com.example.delivery_project.UI.adapters

import com.example.delivery_project.UI.activities.RestaurantDetailActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_project.R
import com.example.delivery_project.ext.cargarImagen
import com.example.delivery_project.interfaces.OnRestaurantClickListener
import com.example.delivery_project.models.Restaurant_Product.Product
import com.example.delivery_project.models.Restaurant_Product.Restaurant

class RestaurantAdapter(
    private var restaurantList: List<Restaurant>,
    private var products: List<Product>,
    private val listener: OnRestaurantClickListener
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_list_item, parent, false)
        return RestaurantViewHolder(view)
    }



    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.bind(restaurant, listener)

        holder.itemView.setOnClickListener {
            listener.onRestaurantClick(restaurant)
            Toast.makeText(holder.itemView.context, "Click en ${restaurant.name}", Toast.LENGTH_SHORT).show()
            val intent = Intent(holder.itemView.context, RestaurantDetailActivity::class.java)
            intent.putExtra("restaurant_id", restaurant.id)
            intent.putExtra("address", restaurant.address)
            intent.putExtra("latitude", restaurant.latitude)
            intent.putExtra("longitude", restaurant.longitude)
            holder.itemView.context.startActivity(intent)

        }




    }

    override fun getItemCount(): Int {
        return restaurantList.size



    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: Restaurant, listener: OnRestaurantClickListener){
            itemView.findViewById<ImageView>(R.id.imgRestaurant).cargarImagen(restaurant.logo)
            itemView.findViewById<TextView>(R.id.restaurant_name).text = restaurant.name
            itemView.findViewById<TextView>(R.id.restaurant_address).text = restaurant.address


        }


    }

}

