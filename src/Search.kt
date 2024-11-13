package com.example.myshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Search : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)
         var searchEditText = findViewById<EditText>(R.id.search_edit_text)
         var priceFilterSeekBar = findViewById<SeekBar>(R.id.price_filter_seekbar)
         var priceValueText = findViewById<TextView>(R.id.price_value_text)
        var searchButton = findViewById<Button>(R.id.search_button)
        var resultsRecyclerView = findViewById<RecyclerView>(R.id.results_recycler_view)
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
        var databaseHelper: ProductDB // Ваш класс для работы с БД
        var productAdapter: ProductAdapter
        val filteredProducts = mutableListOf<Product>() // Список отфильтрованных продуктов

            // Настройка RecyclerView


            // Инициализация базы данных
            databaseHelper = ProductDB(this,null)

            // Установка слушателя для SeekBar
            priceFilterSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    priceValueText.text = "Максимальная цена: $progress"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            // Установка слушателя для кнопки поиска
            searchButton.setOnClickListener {
                val searchQuery = searchEditText.text.toString().trim()
                val maxPrice = priceFilterSeekBar.progress

                // Получение всех продуктов из базы данных
                val allProducts = databaseHelper.getAllProducts()

                // Фильтрация списка продуктов на основе ввода пользователя
                filteredProducts.clear() // Очистка предыдущих результатов
                filteredProducts.addAll(allProducts.filter { product ->
                    product.name.contains(searchQuery, ignoreCase = true) && product.price <= maxPrice
                })
                val uniqueProducts = filteredProducts.distinctBy { it.id } // Удаление дубликатов по уникальному идентификатору
                resultsRecyclerView.layoutManager = LinearLayoutManager(this)
                resultsRecyclerView.adapter = ProductAdapter(uniqueProducts,this)

            }
        val botto = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        botto.setOnNavigationItemSelectedListener { item ->
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
