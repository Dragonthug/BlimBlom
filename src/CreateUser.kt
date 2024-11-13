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

class CreateUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_user)

        val crlogin : EditText = findViewById(R.id.cr_login)
        val crpassword : EditText = findViewById(R.id.cr_password)
        val button : Button = findViewById(R.id.button_cr)
        val auth_page : TextView = findViewById(R.id.auth_page)
        auth_page.setOnClickListener()
        {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        button.setOnClickListener{
            val login = crlogin.text.toString()
            val password = crpassword.text.toString()

            if(login == "" || password == "")
            {
                Toast.makeText(this,"не все поля введены",Toast.LENGTH_LONG).show()
            }
            else{
                val user = User(login,password)
                val db = ClientsDB(this,null)
                db.addUser(user)

                crlogin.text.clear()
                crpassword.text.clear()
                Toast.makeText(this,"пользователь '$login' создан",Toast.LENGTH_LONG).show()
                val intent2 = Intent(this,MainActivity::class.java)
                startActivity(intent2)
            }
        }


    }
}