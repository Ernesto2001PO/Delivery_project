package com.example.delivery_project.UI.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_project.R
import com.example.delivery_project.ext.cargarImagen
import com.example.delivery_project.models.Order.OrderDetailResponse

class OrderDetailsAdapter(
    private val orderDetails: List<OrderDetailResponse>
) : RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLogo: ImageView = itemView.findViewById(R.id.imgLogo)
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtqty : TextView = itemView.findViewById(R.id.txtqty)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_detail, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orderDetail = orderDetails[position]
        holder.txtName.text = orderDetail.product.name
        holder.imgLogo.cargarImagen(orderDetail.product.image)
        holder.txtqty.text = orderDetail.qty.toString()

        Log.d("OrderDetailsAdapter", "onBindViewHolder: ${orderDetail.product.name} ${orderDetail.product.selectedQuantity}")
    }

    override fun getItemCount(): Int = orderDetails.size
}
