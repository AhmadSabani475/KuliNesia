package com.example.kulinesia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Cukup panggil layout saja
        setContentView(R.layout.activity_about)

        // Opsional: Ganti judul di Action Bar jadi "About"
        supportActionBar?.title = "About"
    }
}