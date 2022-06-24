package com.example.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val loginButton = findViewById<Button>(R.id.loginButton)
        val switchRegister = findViewById<SwitchCompat>(R.id.switchRegister)
        val loginEntry = findViewById<EditText>(R.id.loginEntry)
        val passwordEntry = findViewById<EditText>(R.id.passwordEntry)

        val db = DBHelper(this)

        loginButton.setOnClickListener{
            val user = User(
                username = loginEntry.text.toString(),
                password = passwordEntry.text.toString()
            )
            if (loginButton.text == "login"){
                if(db.findUser(user)){
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("loginShow", user.username)
                    startActivity(intent)
                    onStop()
                }
                else{
                    Toast.makeText(
                        applicationContext,
                        "Wrong username or password",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else{
                if (loginEntry.text.isEmpty() || passwordEntry.text.isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Fill all empty spaces",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    if (db.addUser(user)) {
                        Toast.makeText(
                            applicationContext,
                            "Successful Registration",
                            Toast.LENGTH_LONG
                        ).show()
                        loginEntry.text.clear()
                        passwordEntry.text.clear()
                        switchRegister.isChecked = false
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Username already taken",
                            Toast.LENGTH_LONG
                        ).show()
                        passwordEntry.text.clear()
                    }
                }
            }


        }

        switchRegister.setOnCheckedChangeListener{_, isChecked ->
            if (isChecked){
                loginButton.text = "register"
            }else{
                loginButton.text = "login"
            }

        }


    }
}