package com.example.activitat_05

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider

class OptionNotificationActivity : AppCompatActivity() {
    lateinit var back : LinearLayout
    lateinit var slider : Slider
    lateinit var sliderText : TextView
    lateinit var acceptButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_option_notification)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Relacionar las variables con los id's --> Start
        back = findViewById(R.id.aONClose)
        slider = findViewById(R.id.aONSlider)
        sliderText = findViewById(R.id.aONSliderText)
        acceptButton = findViewById(R.id.aONAccept)

        // rV --> End

        // Funciones --> Start
        back.setOnClickListener {
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        slider.valueFrom = 0f
        slider.valueTo = 300f
        slider.stepSize = 1f
        slider.value = 73f

        slider.addOnChangeListener { _, value, _ ->  sliderText.text = value.toString() }

        acceptButton.setOnClickListener {
            var intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        // f --> End
    }
}