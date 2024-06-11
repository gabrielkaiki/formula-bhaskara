package com.gabrielkaiki.formuladebhaskara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            abrirTelaPrincipal()
        }, 3000)
    }

    private fun abrirTelaPrincipal() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}