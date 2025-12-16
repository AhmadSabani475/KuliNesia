package com.example.kulinesia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listKuliner = ArrayList<Kuliner>()
        for (i in dataName.indices) {
            val kuliner = Kuliner(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listKuliner.add(kuliner)
        }
        return listKuliner
    }

    private fun showRecyclerList() {
        rvKuliner.layoutManager = LinearLayoutManager(this)
        val listKulinerAdapter = ListKulinerAdapter(list)
        rvKuliner.adapter = listKulinerAdapter
    }

}