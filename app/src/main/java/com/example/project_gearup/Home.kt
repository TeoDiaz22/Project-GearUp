package com.example.project_gearup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_gearup.models.Motorcycle
import com.example.project_gearup.rv.RVAdapterMotorcycle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {
    val motorcycles = arrayListOf<Motorcycle>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getMotorcycles()
        initRVMotorcyle()
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

    fun getMotorcycles(){
        val db = Firebase.firestore
        val refMotorcycles = db.collection("motorcycles")
        refMotorcycles.get().addOnSuccessListener {
            result ->
            for (document in result){
                val motorcycle = Motorcycle(
                    document.data["image"].toString(),
                    document.data["model"].toString(),
                    document.data["price"].toString().toDouble(),
                    document.data["cylinderCapacity"].toString().toInt(),
                    document.data["motor"].toString(),
                    document.data["motorcycleClass"] as ArrayList<String>,
                    document.data["transmission"].toString(),
                    document.data["suspension"].toString(),
                    document.data["brakes"].toString(),
                    document.data["fuelCapacity"].toString().toDouble(),
                    document.data["description"].toString()
                )
                motorcycles.add(motorcycle)
            }
        }.addOnFailureListener {  }
    }
}