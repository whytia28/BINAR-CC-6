package com.example.binarchapter6.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.binarchapter6.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = "wahyu@binar.co.id"
        val password = "wahyu123"
        val sharedPreferences = getSharedPreferences("suitGame", Context.MODE_PRIVATE)


        btn_login.setOnClickListener {
            if (et_email.text.toString() == email && et_name.text.toString() == password) {
                if (chk_remember_me.isChecked) {
                    val editor = sharedPreferences.edit()

                    editor.putString("email", email)
                    editor.putString("password", password)
                    editor.putString("name", "Wahyu")
                    editor.apply()
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
            } else if (et_email.text.toString().isEmpty() && et_name.text.toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.field_empty), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show()
            }
        }

        btn_reset.setOnClickListener {
            et_email.setText("")
            et_name.setText("")
        }
    }
}