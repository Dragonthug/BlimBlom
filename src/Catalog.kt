package com.example.myshop

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Catalog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalog)
        val catalog = mutableListOf<Catalog_item>()
        val firstcat = Catalog_item("Техника для дома","c1",1)
        catalog.add(firstcat)
        val productView = findViewById<RecyclerView>(R.id.recycler_view_cart)
        productView.layoutManager = LinearLayoutManager(this)
        productView.adapter = CatalogAdapter(catalog,this)
        val bottom = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    // Логика для перехода на экран поиска
                    val intent = Intent(this,MainPage::class.java)
                    startActivity(intent)
                    true
                }
                R.id.search -> {
                    val intent1 = Intent(this,Search::class.java)
                    startActivity(intent1)
                    true
                }
                R.id.catalog -> {
                    val intent2 = Intent(this,Catalog::class.java)
                    startActivity(intent2)
                    true
                }
                R.id.bascket -> {
                    val intent3 = Intent(this,Buscket::class.java)
                    startActivity(intent3)
                    true
                }
                else -> false
            }
        }
    }
}