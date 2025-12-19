package com.example.kulinesia

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Hilangkan Action Bar agar full screen (Opsional tapi lebih rapi)
        supportActionBar?.hide()

        // Tahan layar selama 3000ms (3 detik), lalu pindah
        Handler(Looper.getMainLooper()).postDelayed({
            // Pindah ke MainActivity
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)

            // Tutup SplashActivity agar user tidak bisa kembali ke sini saat tekan Back
            finish()
        }, 3000)
    }
}