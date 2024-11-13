package com.example.myshop

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CatalogAdapter(var catalog: List<Catalog_item>,var context: Context):RecyclerView.Adapter<CatalogAdapter.ViewHolder>( ) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var catalogImage: ImageView = itemView.findViewById(R.id.product_image_c)
        var catalogName: TextView = itemView.findViewById(R.id.product_name_c)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_catalog, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.catalogName.text = catalog[position].name
        val ImageId = context.resources.getIdentifier(
            catalog[position].image,
            "drawable",
            context.packageName
        )
        holder.catalogImage.setImageResource(ImageId)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProdFromCat::class.java)
            intent.putExtra("ProductId", catalog[position].id)
            intent.putExtra("ProductName", catalog[position].name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return catalog.count()
    }


}
