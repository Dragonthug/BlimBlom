package com.example.myshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class Buscket : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bucket)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_cart)

        val checkoutButton = findViewById<Button>(R.id.checkout_button)
        var filteredProducts = mutableListOf<Product>()
        val db = BasketBD(this,null)
        val products = db.getAllProducts()
        db.close()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BuscketAdapter(products,this)
        val bottom = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    // Логика для перехода на экран поиска
                    val intent = Intent(this, MainPage::class.java)
                    startActivity(intent)
                    true
                }

                R.id.search -> {
                    val intent1 = Intent(this, Search::class.java)
                    startActivity(intent1)
                    true
                }

                R.id.catalog -> {
                    val intent2 = Intent(this, Catalog::class.java)
                    startActivity(intent2)
                    true
                }

                R.id.bascket -> {
                    val intent3 = Intent(this, Buscket::class.java)
                    startActivity(intent3)
                    true
                }

                else -> false
            }
        }
    }


}

