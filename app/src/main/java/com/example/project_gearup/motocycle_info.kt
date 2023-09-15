package com.example.project_gearup

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.project_gearup.models.Motorcycle

class motocycle_info : AppCompatActivity() {

    var motorcycle : Motorcycle? = null
    var motorcycleImg : ImageView? = null
    var motorcycleTitle : TextView? = null
    var motorcyclePrice : TextView? = null
    var motorcycleMotor : TextView? = null
    var motorcycleCicli : TextView? = null
    var motorcycleTrans : TextView? = null
    var motorcycleSusp : TextView? = null
    var motorcycleBrake : TextView? = null
    var motorcycleCapa : TextView? = null
    var motorcycleDescrip : TextView? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motocycle_info)
        motorcycle = intent.getParcelableExtra("motorcycle",Motorcycle::class.java)
        val motorcycleId = intent.getStringExtra("motorcycleId").toString()
        println(motorcycleId)
        motorcycleImg = findViewById<ImageView>(R.id.img_motorcycle)
        motorcycleTitle = findViewById<TextView>(R.id.tv_motorcycle_title)
        motorcyclePrice = findViewById<TextView>(R.id.tv_motocycle_price)
        motorcycleMotor = findViewById<TextView>(R.id.tv_motocycle_motor)
        motorcycleCicli = findViewById<TextView>(R.id.tv_motocycle_cilindraje)
        motorcycleTrans = findViewById<TextView>(R.id.tv_motocycle_transmision)
        motorcycleSusp = findViewById<TextView>(R.id.tv_motocycle_suspension)
        motorcycleBrake = findViewById<TextView>(R.id.tv_motocycle_brake)
        motorcycleCapa = findViewById<TextView>(R.id.tv_motocycle_capacity)
        motorcycleDescrip = findViewById<TextView>(R.id.tv_motocycle_description)
        fillData()
        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener{ finish() }
        val btnReview = findViewById<Button>(R.id.btn_review)
        btnReview.setOnClickListener { openParameterActivity(ReviewView::class.java,motorcycleId) }
    }

    private fun fillData(){
        Glide.with(motorcycleImg!!.context).load(motorcycle?.image).load(motorcycleImg)
        motorcycleTitle!!.text = motorcycle?.model!!.uppercase()
        motorcyclePrice!!.text = '$' + motorcycle?.price.toString()
        motorcycleMotor!!.text = motorcycle?.motor
        motorcycleCicli!!.text = motorcycle?.cylinderCapacity.toString() + "cc"
        motorcycleTrans!!.text = motorcycle?.transmission
        motorcycleSusp!!.text = motorcycle?.suspension
        motorcycleBrake!!.text = motorcycle?.brakes
        motorcycleCapa!!.text = motorcycle?.fuelCapacity.toString() + "gal"
        motorcycleDescrip!!.text = motorcycle?.description
    }

    fun openParameterActivity(clase: Class<*>, motorcycleid: String){
        val intent = Intent(this,clase)
        intent.putExtra("motorcycle",motorcycle)
        intent.putExtra("motorcycleId",motorcycleid)
        startActivity(intent)
    }
}