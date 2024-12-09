package com.example.delivery_project.UI.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_project.R
import com.example.delivery_project.UI.activities.MapsActivity
import com.example.delivery_project.models.Order.OrderResponse


class OrderAdapter(
    var orders: List<OrderResponse>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {


    fun updateOrders(newOrders: List<OrderResponse>) {
        orders = newOrders
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item, parent, false)
        return OrderViewHolder(view)
    }


    override fun onBindViewHolder(holder: OrderAdapter.OrderViewHolder, position: Int) {

        val order = orders[position]
        holder.bind(order)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MapsActivity::class.java)
            intent.putExtra("latitude", order.latitude)
            intent.putExtra("longitude", order.longitude)
            holder.itemView.context.startActivity(intent)


        }

    }

    override fun getItemCount(): Int {
        return orders.size
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtAddress: TextView = itemView.findViewById(R.id.txtaddress)
        private val txtStatus: TextView = itemView.findViewById(R.id.txtstatus)
        private val txtCreatedAt: TextView = itemView.findViewById(R.id.txtcreatedat)
        private val txtTotal: TextView = itemView.findViewById(R.id.txtTotal)
        private val rvOrderDetails: RecyclerView = itemView.findViewById(R.id.rvOrderDetails)

        @SuppressLint("SetTextI18n")
        fun bind(orderResponse: OrderResponse) {

            txtAddress.text = orderResponse.address
            val statusText = when (orderResponse.status) {
                "1" -> "Solicitado"
                "2" -> "Aceptado por chofer"
                "3" -> "En camino"
                "4" -> "Pedido finalizado"
                else -> "Estado desconocido"
            }
            txtStatus.text = "Estado: $statusText"
            txtCreatedAt.text = orderResponse.created_at
            txtTotal.text = "Total: $${orderResponse.total}"

            val orderDetailsAdapter = OrderDetailsAdapter(orderResponse.order_details)
            rvOrderDetails.layoutManager = LinearLayoutManager(itemView.context)
            rvOrderDetails.adapter = orderDetailsAdapter


        }


    }


}