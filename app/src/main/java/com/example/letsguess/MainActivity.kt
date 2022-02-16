package com.example.letsguess

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var customRateUsView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.customToolBar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.custom_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_rules -> {
                Toast.makeText(
                    applicationContext,
                    "Clicked on Rules",
                    Toast.LENGTH_SHORT
                ).show()
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
            Toast.makeText(this@MainActivity, "Thanks To rate us :)", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}