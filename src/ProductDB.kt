package com.example.myshop

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductDB (val context : Context, factory : SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper(context,"allproducts",factory,1)
{
    companion object {
        private const val DATABASE_NAME = "shop.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_PRODUCTS = "products"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_DESC = "description"
        private const val COLUMN_TEXT = "text" // Добавлено поле text
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = """
            CREATE TABLE $TABLE_PRODUCTS (
                $COLUMN_ID INTEGER PRIMARY KEY,
                $COLUMN_NAME TEXT,
                $COLUMN_DESC TEXT,
                $COLUMN_TEXT TEXT,
                $COLUMN_PRICE INTEGER,
                $COLUMN_IMAGE INTEGER
            )
        """.trimIndent()
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PRODUCTS")
        onCreate(db)
    }

    // Метод для получения всех товаров
    fun addProduct(product: Product) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, product.name)
            put(COLUMN_DESC, product.desc)
            put(COLUMN_TEXT, product.text)
            put(COLUMN_PRICE, product.price)
            put(COLUMN_IMAGE, product.image) // image теперь строка
        }

        // Вставка в таблицу
        db.insert(TABLE_PRODUCTS, null, values)
        db.close() // Закрываем базу данных после вставки
    }
    fun getAllProducts(): List<Product> {
        val productList = mutableListOf<Product>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_PRODUCTS", null)

        if (cursor.moveToFirst()) {
            do {
                val idIndex = cursor.getColumnIndex(COLUMN_ID)
                val nameIndex = cursor.getColumnIndex(COLUMN_NAME)
                val descIndex = cursor.getColumnIndex(COLUMN_DESC)
                val textIndex = cursor.getColumnIndex(COLUMN_TEXT)
                val priceIndex = cursor.getColumnIndex(COLUMN_PRICE)
                val imageIndex = cursor.getColumnIndex(COLUMN_IMAGE)

                // Проверяем, что индексы не равны -1
                if (idIndex != -1 && nameIndex != -1 && descIndex != -1 && textIndex != -1 && priceIndex != -1 && imageIndex != -1) {
                    val id = cursor.getInt(idIndex)
                    val name = cursor.getString(nameIndex)
                    val desc = cursor.getString(descIndex)
                    val text = cursor.getString(textIndex)
                    val price = cursor.getInt(priceIndex)
                    val image = cursor.getString(imageIndex)

                    val product = Product(id, name, image, desc, text, price)
                    productList.add(product)
                }

            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return return productList.distinctBy { it.name }
    }
    fun updateProductPriceByName(productName: String, newPrice: Int): Boolean {
        val db = this.writableDatabase

        // Проверка на существование продукта
        val cursor = db.rawQuery("SELECT * FROM $TABLE_PRODUCTS WHERE $COLUMN_NAME = ?", arrayOf(productName))
        return if (cursor.count > 0) {
            // Продукт существует, обновляем его цену
            val values = ContentValues().apply {
                put(COLUMN_ID, newPrice)
            }

            val rowsAffected = db.update(TABLE_PRODUCTS, values, "$COLUMN_NAME = ?", arrayOf(productName))
            cursor.close() // Закрытие курсора
            db.close() // Закрытие базы данных
            rowsAffected > 0 // Возвращаем true, если обновление прошло успешно
        } else {
            cursor.close() // Закрытие курсора, если продукт не найден
            db.close() // Закрытие базы данных
            false // Возвращаем false, если продукт не найден
        }
    }
    fun clearProducts() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_PRODUCTS") // Удаляем все записи
        db.close() // Закрываем базу данных
    }
}
