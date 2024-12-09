// ProductAdapter.kt
package com.example.delivery_project.UI.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_project.R
import com.example.delivery_project.ext.cargarImagen
import com.example.delivery_project.models.Restaurant_Product.Product

class ProductsAdapter(
    var productList: List<Product>
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {


    fun getSelectedProducts(): List<Product> {
        return productList.filter { it.selectedQuantity > 0 }
    }


    // Actualizar la lista de productos
    fun updateProducts(newProductList: List<Product>) {
        productList = newProductList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ProductViewHolder(view)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)


    }

    override fun getItemCount() = productList.size


    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val btnPlus = itemView.findViewById<Button>(R.id.btnPlus)
        val btnMinus = itemView.findViewById<Button>(R.id.btnLess)
        val txtNumber = itemView.findViewById<TextView>(R.id.txtNumber)

        fun bind(product: Product) {
            itemView.findViewById<ImageView>(R.id.imgProduct).cargarImagen(product.image)
            itemView.findViewById<TextView>(R.id.product_name).text = product.name
            itemView.findViewById<TextView>(R.id.product_description).text = product.description
            itemView.findViewById<TextView>(R.id.product_price).text = "S/. ${product.price}"


            btnPlus.setOnClickListener {
                    product.quantity++
                    txtNumber.text = product.quantity.toString()


            }

            btnMinus.setOnClickListener {
                if (product.quantity > 0) {
                    product.quantity--
                    txtNumber.text = product.quantity.toString()
                }
            }




        }


    }
}

