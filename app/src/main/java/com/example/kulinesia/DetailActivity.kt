package com.example.kulinesia

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_KULINER = "extra_kuliner"
    }

    // 1. PINDAHKAN VARIABEL INI KE LUAR onCreate
    // Agar bisa diakses oleh fungsi Share nanti
    private var selectedKuliner: Kuliner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvName: TextView = findViewById(R.id.tv_detail_name)
        val tvDesc: TextView = findViewById(R.id.tv_detail_description)
        val tvOrigin: TextView = findViewById(R.id.tv_origin)
        val tvRecipe: TextView = findViewById(R.id.tv_detail_recipe)
        val imgPhoto: ImageView = findViewById(R.id.img_detail_photo)
        val tvIngredients: TextView = findViewById(R.id.tv_detail_ingredients)
        val tvTime: TextView = findViewById(R.id.tv_time)
        val tvServings: TextView = findViewById(R.id.tv_servings)
        val tvDifficulty: TextView = findViewById(R.id.tv_difficulty)
        val btnYoutube: Button = findViewById(R.id.btn_youtube)

        // 2. Ambil Data (Simpan ke variabel global selectedKuliner)
        selectedKuliner = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_KULINER, Kuliner::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_KULINER)
        }

        // 3. Tampilkan Data (Gunakan selectedKuliner)
        if (selectedKuliner != null) {
            // Kita pakai "kuliner" sebagai nama pendek biar kode bawah tidak perlu diubah banyak
            val kuliner = selectedKuliner!!

            tvName.text = kuliner.name
            tvDesc.text = kuliner.description
            tvOrigin.text = kuliner.origin
            tvRecipe.text = kuliner.recipe
            imgPhoto.setImageResource(kuliner.photo)
            tvIngredients.text = kuliner.ingredients
            tvTime.text = kuliner.time
            tvServings.text = kuliner.servings
            tvDifficulty.text = kuliner.difficulty

            btnYoutube.setOnClickListener {
                val url = kuliner.url
                if (url.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                }
            }

            supportActionBar?.title = kuliner.name
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    // 4. MEMUNCULKAN MENU SHARE
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // 5. MENANGANI KLIK TOMBOL SHARE
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                // Logika Share dijalankan di sini
                if (selectedKuliner != null) {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"

                    // Isi pesan yang mau dibagikan
                    val shareMessage = "Halo! Cek resep ${selectedKuliner?.name} khas ${selectedKuliner?.origin} di aplikasi KuliNesia.\n\nBahan-bahannya:\n${selectedKuliner?.ingredients}"

                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}