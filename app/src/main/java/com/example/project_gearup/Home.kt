package com.example.project_gearup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_gearup.models.Motorcycle
import com.example.project_gearup.rv.RVAdapterMotorcycle

class Home : AppCompatActivity() {
    val motorcycles = arrayListOf<Motorcycle>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun initRVMotorcyle(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_motorcycles)
        val adapter = RVAdapterMotorcycle(
            this,
            motorcycles,
            recyclerView
        )
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter.notifyDataSetChanged()
    }
}