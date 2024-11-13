package com.example.myshop

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

class ProdFromCat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_prod_from_cat)

        val text = findViewById<TextView>(R.id.textpfc)
        val name = intent.getStringExtra("ProductName")
        text.text = name
        val productView = findViewById<RecyclerView>(R.id.list_profrocot)
        var db: ProductDB
        db = ProductDB(this,null)
        val products = db.getAllProducts()

        val filteredProducts = mutableListOf<Product>()
        val catid = intent.getIntExtra("ProductId",0)
        filteredProducts.clear() // Очистка предыдущих результатов
        filteredProducts.addAll(products.filter { product ->
            product.id == catid
        })
        productView.layoutManager = LinearLayoutManager(this)
        productView.adapter = ProductAdapter(filteredProducts,this)
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