package com.example.myshop

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val userLogin: EditText = findViewById(R.id.auth_login)
        val userPassword: EditText = findViewById(R.id.auth_user_password)
        val authButton: Button = findViewById(R.id.auth_button)
        val GoToCreateUser: TextView = findViewById(R.id.go_to_create_page)

        GoToCreateUser.setOnClickListener{
            val intent1 = Intent(this,CreateUser::class.java)
            startActivity(intent1)
        }
        authButton.setOnClickListener{
            val login = userLogin.text.toString()
            val password = userPassword.text.toString()

            if(login == "" || password == "")
            {
                Toast.makeText(this,"не все поля введены",Toast.LENGTH_LONG).show()
            }
            else{
                val db = ClientsDB(this,null)
                val isAuth = db.getUser(login,password)
                if(isAuth) {
                    val bd = BasketBD(this,null)
                    bd.clearProducts()
                    bd.close()
                    val intent2 = Intent(this,MainPage::class.java)
                    startActivity(intent2)
                }else{
                    Toast.makeText(this,"неверный логин или пароль",Toast.LENGTH_LONG).show()
            }
        }

        }

    }    
}
