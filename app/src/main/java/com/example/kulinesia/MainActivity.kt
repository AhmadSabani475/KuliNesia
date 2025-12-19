package com.example.kulinesia

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class MainActivity : AppCompatActivity() {
    private lateinit var rvKuliner: RecyclerView
    private val list = ArrayList<Kuliner>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvKuliner = findViewById(R.id.rv_kuliner)
        rvKuliner.setHasFixedSize(true)

        list.addAll(getListKuliner())
        showRecyclerList()
    }

    private fun getListKuliner(): ArrayList<Kuliner> {
        // 1. Ambil data dari strings.xml
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        // Data Tambahan (Detail)
        val dataOrigin = resources.getStringArray(R.array.data_origin)
        val dataRecipe = resources.getStringArray(R.array.data_recipe)
        val dataUrl = resources.getStringArray(R.array.data_url) // JANGAN LUPA INI (Link Youtube)

        // Data Info Bar
        val dataTime = resources.getStringArray(R.array.data_time)
        val dataServings = resources.getStringArray(R.array.data_servings)
        val dataDifficulty = resources.getStringArray(R.array.data_difficulty)
        val dataIngredients = resources.getStringArray(R.array.data_ingredients)
        val listKuliner = ArrayList<Kuliner>()

        // 2. Masukkan semua ke dalam list
        // Gunakan Named Argument (nama_variabel = nilai) agar tidak tertukar urutannya
        for (i in dataName.indices) {
            val kuliner = Kuliner(
                name = dataName[i],
                description = dataDescription[i],
                photo = dataPhoto.getResourceId(i, -1),
                origin = dataOrigin[i],
                recipe = dataRecipe[i],
                url = dataUrl[i],          // Link Youtube masuk sini
                time = dataTime[i],
                servings = dataServings[i],
                difficulty = dataDifficulty[i],
                ingredients = dataIngredients[i]
            )
            listKuliner.add(kuliner)
        }

        // Wajib me-recycle TypedArray gambar untuk hemat memori
        dataPhoto.recycle()

        return listKuliner
    }

    private fun showRecyclerList() {
        rvKuliner.layoutManager = LinearLayoutManager(this)
        val listKulinerAdapter = ListKulinerAdapter(list)
        rvKuliner.adapter = listKulinerAdapter

        listKulinerAdapter.setOnItemClickCallback(object : ListKulinerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Kuliner) {
                showSelectedKuliner(data)
            }
        })
    }

    private fun showSelectedKuliner(kuliner: Kuliner) {
        // Pindah ke DetailActivity membawa data Parcelable
        val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DetailActivity.EXTRA_KULINER, kuliner)
        startActivity(moveWithObjectIntent)
    }

    // --- MENU OPTION (About Profile) ---

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        // Load Foto Profil Bulat ke Icon Menu menggunakan Glide
        val aboutItem = menu.findItem(R.id.about_page)

        Glide.with(this)
            .asDrawable()
            .load(R.drawable.foto_diri) // Pastikan file foto_diri ada di drawable
            .circleCrop()
            .into(object : CustomTarget<Drawable>(100, 100) {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    aboutItem.icon = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {
                    // Wajib di-override meski kosong
                }
            })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}