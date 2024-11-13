package com.example.myshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)

            val name:TextView = findViewById(R.id.product_name_one)
            val desc:TextView = findViewById(R.id.product_desc_one)
            val text:TextView = findViewById(R.id.product_text_one)
            val price:TextView = findViewById(R.id.product_price_one)
            val image:ImageView = findViewById(R.id.product_image_one)
            val button:Button = findViewById(R.id.button_one)

            name.text = intent.getStringExtra("ProductName")
            text.text = intent.getStringExtra("ProductDesc")

            price.text = "${intent.getIntExtra("ProductPrice",0)} $"
            val im = intent.getIntExtra("ProductImage",0)
        image.setImageResource(im)
        val imag = intent.getStringExtra("Image")
        val nam = intent.getStringExtra("ProductName").toString()
        val des = intent.getStringExtra("ProductDesc").toString()
        button.setOnClickListener()
        {
            val db = BasketBD(this,null)
            db.addProduct(Product(0,nam,imag.toString(),des,"",intent.getIntExtra("ProductPrice",0)))
            db.close()
        }
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
