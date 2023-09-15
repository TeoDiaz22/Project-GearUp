package com.example.project_gearup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_gearup.models.Motorcycle
import com.example.project_gearup.rv.RVAdapterMotorcycle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {
    val motorcycles = arrayListOf<Motorcycle>()
    var idItemSelected = 0
    lateinit var adapter : RVAdapterMotorcycle
    var motorcyclesIds = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //getMotorcycles()
        initRVMotorcyle()
    }

    override fun onResume() {
        super.onResume()
        getMotorcycles()
    }

    fun initRVMotorcyle(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_motorcycles)
        adapter = RVAdapterMotorcycle(
            this,
            motorcycles,
            recyclerView
        )
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //adapter.notifyDataSetChanged()
    }

    fun openActivityMotorcycle(
        classToOpen: Class<*>
    ){
        val intent = Intent(this, classToOpen)

        //val client = Database.listClients[idItemSelected]
        val motorcycle = motorcycles[idItemSelected]
        val motorcycleId = motorcyclesIds[idItemSelected]
        intent.putExtra("motorcycle", motorcycle)
        intent.putExtra("motorcycleId",motorcycleId)
        startActivity(intent)
    }

    fun getMotorcycles(){
        val db = Firebase.firestore
        val refMotorcycles = db.collection("motorcycles")
        motorcycles.clear()
        refMotorcycles.get().addOnSuccessListener {
            result ->
            for (document in result){
                val motorcycle = Motorcycle(
                    document.data["image"].toString(),
                    document.data["model"].toString(),
                    document.data["price"].toString().toDouble(),
                    document.data["cylinderCapacity"].toString().toInt(),
                    document.data["motor"].toString(),
                    document.data["motorcycleClass"].toString(),
                    document.data["transmission"].toString(),
                    document.data["suspension"].toString(),
                    document.data["brakes"].toString(),
                    document.data["fuelCapacity"].toString().toDouble(),
                    document.data["description"].toString()
                )
                motorcyclesIds.add(document.id)
                motorcycles.add(motorcycle)
            }
            adapter.notifyDataSetChanged()
        }.addOnFailureListener {  }
    }
}