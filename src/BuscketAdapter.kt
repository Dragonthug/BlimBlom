package com.example.myshop

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BuscketAdapter(var products: List<Product>,var context: Context): RecyclerView.Adapter<BuscketAdapter.ViewHolder>( ) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage: ImageView = itemView.findViewById(R.id.product_image_b)
        var productName: TextView = itemView.findViewById(R.id.product_name_b)
        var productDescription: TextView = itemView.findViewById(R.id.product_description_b)
        var productPrice: TextView = itemView.findViewById(R.id.product_price_b)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_buscet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.productName.text = products[position].name
        holder.productDescription.text = products[position].desc
        holder.productPrice.text = "${products[position].price} $"
        val ImageId = context.resources.getIdentifier(
            products[position].image,
            "drawable",
            context.packageName
        )
        holder.productImage.setImageResource(ImageId)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra("ProductName", products[position].name)
            intent.putExtra("ProductPrice", products[position].price)
            intent.putExtra("ProductImage", ImageId)
            intent.putExtra("ProductText", products[position].text)
            intent.putExtra("Image",products[position].image)

            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return products.count()
    }


}