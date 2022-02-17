package com.example.letsguess

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var customRateUsView: View
    private lateinit var tvAndroidNumberDisplay: TextView
    private lateinit var cardNumber1: CardView
    private lateinit var cardNumber2: CardView
    private lateinit var cardNumber3: CardView
    private lateinit var cardNumber4: CardView
    private lateinit var cardNumber5: CardView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgressBarPercentage: TextView
    private var androidGuess: Int = 0
    private var userGuess: Int = 0
    private var progressPercentage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.customToolBar))

        cardNumber1 = findViewById(R.id.cardNumber1)
        cardNumber2 = findViewById(R.id.cardNumber2)
        cardNumber3 = findViewById(R.id.cardNumber3)
        cardNumber4 = findViewById(R.id.cardNumber4)
        cardNumber5 = findViewById(R.id.cardNumber5)
        tvAndroidNumberDisplay = findViewById(R.id.tv_androidNumberDisplay)
        progressBar = findViewById(R.id.progressBar)
        tvProgressBarPercentage = findViewById(R.id.tv_progressBarPercentage)

        tvProgressBarPercentage.text = progressPercentage.toString()

        cardNumber1.setOnClickListener {
            androidGuess(1)
        }
        cardNumber2.setOnClickListener {
            androidGuess(2)
        }
        cardNumber3.setOnClickListener {
            androidGuess(3)
        }
        cardNumber4.setOnClickListener {
            androidGuess(4)
        }
        cardNumber5.setOnClickListener {
            androidGuess(5)
        }

    }

    private fun showCustomToast() {
        val layout: View =
            layoutInflater.inflate(
                R.layout.custom_welcome_toast_layout,
                findViewById(R.id.customWelcomeToast)
            )

        Toast(this).apply {
            duration = Toast.LENGTH_LONG
            Gravity.BOTTOM
            view = layout
            show()
        }
    }

    private fun androidGuess(user: Int) {
        userGuess = user
        androidGuess = (1..5).random()

        if (userGuess == androidGuess && progressPercentage <= 80) {
            progressPercentage += 20
            progressBar.progress = progressPercentage
            tvProgressBarPercentage.text = "$progressPercentage%"
        }
        tvAndroidNumberDisplay.text =
            "Android Guess: $androidGuess User Guess: $userGuess"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_rules -> {
                val intent = Intent(this@MainActivity, RulesScreenActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_rate_us -> {
                rateUsCustomAlertDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun rateUsCustomAlertDialog() {

        customRateUsView = layoutInflater.inflate(
            R.layout.custom_rate_us_alert_dialog_layout,
            null
        )

        val submitButton = customRateUsView.findViewById<CardView>(R.id.cardButton_submit)
        val cancelButton = customRateUsView.findViewById<CardView>(R.id.cardButton_cancel)
        val rateUs = customRateUsView.findViewById<RatingBar>(R.id.rb_rateUs)

        submitButton.isEnabled = false

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setView(customRateUsView)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)

        rateUs.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { _: RatingBar?, rating: Float, fromUser: Boolean ->
                if (rating > 0 && fromUser) submitButton.isEnabled = true
            }

        submitButton.setOnClickListener {
            showCustomToast()
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}