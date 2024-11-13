package com.example.myshop


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_page)

val productView = findViewById<RecyclerView>(R.id.list_product)
        val db = ProductDB(this,null)
        db.clearProducts()
        db.addProduct(Product(1,"Электрочайник POLARIS PWK 1739CGL Сталь","ch1","Мощность:  2200 Вт\n" +
                "Нагревательный элемент:  Закрытый\n" +
                "Объем воды:  1.8 л\n" +
                "Материал корпуса:  Нержавеющая сталь, Пластик, Стекло\n" +
                "Терморегулятор:  Нет","Электрический чайник Polaris PWK 1739CGL - это стильный и функциональный прибор для приготовления чая и горячих напитков.\n" +
                "Благодаря подсветке воды внутри чайника, вы сможете всегда контролировать уровень воды и поддерживать оптимальную температуру для заваривания чая.\n" +
                "Нагревательный элемент чайника Polaris PWK 1739CGL закрытый, что обеспечивает безопасность использования и удобство в чистке.+\n" +
                "Материал колбы из стекла позволяет наблюдать за процессом закипания воды, а также легко очищать внутреннюю поверхность.\n" +
                "Подставка с возможностью вращения обеспечивает удобство использования чайника в любом положении, а мощность 2200 Вт позволяет быстро нагревать воду.\n" +
                "Материал корпуса из металла, пластика и стекла делает чайник Polaris PWK 1739CGL прочным и надежным в использовании.\n" +
                "Управление чайником механическое, что делает его простым и удобным в эксплуатации.",1))
        db.addProduct(Product(1,"Миксер планетарный Hyundai HYM-S6551","mix2","Тип миксера:  Стационарный\n" +
                "Мощность:  1300 Вт\n" +
                "Импульсный режим: Да\n" +
                "Количество скоростей:  8\n" +
                "Количество насадок:  3","Миксер планетарный Hyundai HYM-S6551 имеет 8 скоростей работы и подходит для смешивания как жидкого, так и плотного теста. Благодаря высокой мощности прибор легко взбивает белки до устойчивых пиков, замешивает дрожжевое или песочное тесто.\n" +
                "\n" +
                "Смешивание продуктов происходит в чаше объемом 4,5 л. Для нее предусмотрена крышка, которая предотвращает забрызгивание кухонных поверхностей. Также в комплекте идет венчик, лопатка для перемешивания и крюк.\n" +
                "\n" +
                "Прибор получил противоскользящие ножки, благодаря чему прочно стоит на столе во время работы.",2))
        db.addProduct(Product(2,"Утюг Philips DST3041/80","ut1","Максимальная мощность:  2600 Вт\n" +
                "Рабочая поверхность:  Керамика\n" +
                "Емкость резервуара для воды:  300 мл\n" +
                "Мощность подачи пара:  40 г/мин\n" +
                "Паровой удар: Да\n" +
                "Интенсивность парового удара:  200 г/мин\n" +
                "Вертикальное отпаривание: Да","Утюг Philips DST3041/80 имеет высокую мощность в 2600 Вт, что позволяет ему эффективно справляться со складками. Модель обладает функцией пара, особой формой подошвы и большим резервуаром для воды в 300 мл, благодаря чему таким прибором очень удобно разглаживать вещи.\n" +
                "\n" +
                "Гладкая подошва.\n" +
                "\n" +
                "Модель получила керамическую подошву, которая легко скользит, устойчива к царапинам и легко очищается. Носик подошвы заострен и может разглаживать ткань в труднодоступных местах, например, между пуговицами.\n" +
                "\n" +
                "Функция пара.\n" +
                "\n" +
                "Утюг подает пар с интенсивностью 40 г/мин. Это помогает легко гладить сильно мятые, пересушенные вещи. Предусмотрен паровой удар силой до 200 гр. для устранения заломов и обработки тяжелых тканей. Утюг можно использовать вертикально, чтобы гладить вещи на вешалках. Благодаря системе «капля-стоп» вода не протекает.\n" +
                "\n" +
                "Система очистки от накипи.\n" +
                "\n" +
                "Встроенный слайдер очистки помогает легко очищать утюг от известкового налета, чтобы прибор работал с неизменно высокой эффективностью.",3))
        val products = db.getAllProducts()
        db.close()
        productView.layoutManager = LinearLayoutManager(this)
        productView.adapter = ProductAdapter(products,this)
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