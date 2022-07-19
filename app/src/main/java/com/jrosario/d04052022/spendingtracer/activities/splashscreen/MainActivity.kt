package com.jrosario.d04052022.spendingtracer.activities.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.jrosario.d04052022.spendingtracer.R
import com.jrosario.d04052022.spendingtracer.activities.spendingtracer.SpendingTracerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val i = Intent(this@MainActivity, SpendingTracerActivity::class.java)
                startActivity(i)
                finish()
            },
            2000
        )
    }
}