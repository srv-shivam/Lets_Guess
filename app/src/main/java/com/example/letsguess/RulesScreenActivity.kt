package com.example.letsguess

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class RulesScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules_screen)

        val layout: View =
            layoutInflater.inflate(
                R.layout.custom_welcome_toast_layout,
                findViewById(R.id.customWelcomeToast)
            )

        findViewById<AppCompatButton>(R.id.btnPlay).setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

            Toast(this).apply {
                duration = Toast.LENGTH_SHORT
                view = layout
                show()
            }
        }
    }
}
