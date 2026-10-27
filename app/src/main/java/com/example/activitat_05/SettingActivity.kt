package com.example.activitat_05

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SettingActivity : AppCompatActivity() {
    lateinit var buttonBack : ImageView
    lateinit var optionLanguages : LinearLayout
    lateinit var optionSecurity : LinearLayout
    lateinit var optionPrivacy : LinearLayout
    lateinit var optionNotification : LinearLayout
    lateinit var optionPreference : LinearLayout
    lateinit var optionFriend : LinearLayout
    lateinit var optionLogOut : LinearLayout
    lateinit var optionChangeCount : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_setting)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Relacionar variables con los id's --> Start
        buttonBack = findViewById(R.id.aSBack)
        optionLanguages = findViewById(R.id.aSOptionLanguages)
        optionSecurity = findViewById(R.id.aSOptionSecurity)
        optionPrivacy = findViewById(R.id.aSOptionPrivacy)
        optionNotification = findViewById(R.id.aSOptionNotification)
        optionPreference = findViewById(R.id.aSOptionPreference)
        optionFriend = findViewById(R.id.aSOptionFriend)
        optionChangeCount = findViewById(R.id.aSOChangeCount)
        optionLogOut = findViewById(R.id.aSOLogOut)

        // rV --> End

        // Funciones --> Start
        buttonBack.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        optionLanguages.setOnClickListener {
            var intent = Intent(this, OptionLanguagesActivity::class.java)
            startActivity(intent)
        }

        optionSecurity.setOnClickListener {
            var intent = Intent(this, OptionSecurityActivity::class.java)
            startActivity(intent)
        }

        optionPrivacy.setOnClickListener {
            var intent = Intent(this, OptionPrivacyActivity::class.java)
            startActivity(intent)
        }

        optionNotification.setOnClickListener {
            var intent = Intent(this, OptionNotificationActivity::class.java)
            startActivity(intent)
        }

        optionPreference.setOnClickListener {
            var intent = Intent(this, OptionPreferenceActivity::class.java)
            startActivity(intent)
        }

        optionFriend.setOnClickListener {
            var intent = Intent(this, OptionFriendActivity::class.java)
            startActivity(intent)
        }

        optionLogOut.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        optionChangeCount.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // f --> End

    }
}