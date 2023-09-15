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
    val motorcycleImg = findViewById<ImageView>(R.id.img_motorcycle)
    val motorcycleTitle = findViewById<TextView>(R.id.tv_motorcycle_title)
    val motorcyclePrice = findViewById<TextView>(R.id.tv_motocycle_price)
    val motorcycleMotor = findViewById<TextView>(R.id.tv_motocycle_motor)
    val motorcycleCicli = findViewById<TextView>(R.id.tv_motocycle_cilindraje)
    val motorcycleTrans = findViewById<TextView>(R.id.tv_motocycle_transmision)
    val motorcycleSusp = findViewById<TextView>(R.id.tv_motocycle_suspension)
    val motorcycleBrake = findViewById<TextView>(R.id.tv_motocycle_brake)
    val motorcycleCapa = findViewById<TextView>(R.id.tv_motocycle_capacity)
    val motorcycleDescrip = findViewById<TextView>(R.id.tv_motocycle_description)


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motocycle_info)
        motorcycle = intent.getParcelableExtra("motorcycle",Motorcycle::class.java)
        fillData()
        val btnBack = findViewById<ImageButton>(R.id.btn_back)
        btnBack.setOnClickListener{ finish() }
        val btnReview = findViewById<Button>(R.id.btn_review)
        btnReview.setOnClickListener { openParameterActivity(Review::class.java) }
    }

    private fun fillData(){
        Glide.with(motorcycleImg.context).load(motorcycle?.image).load(motorcycleImg)
        motorcycleTitle.text = motorcycle?.model!!.uppercase()
        motorcyclePrice.text = '$' + motorcycle?.price.toString()
        motorcycleMotor.text = motorcycle?.motor
        motorcycleCicli.text = motorcycle?.cylinderCapacity.toString() + "cc"
        motorcycleTrans.text = motorcycle?.transmission
        motorcycleSusp.text = motorcycle?.suspension
        motorcycleBrake.text = motorcycle?.brakes
        motorcycleCapa.text = motorcycle?.fuelCapacity.toString() + "gal"
        motorcycleDescrip.text = motorcycle?.description
    }

    fun openParameterActivity(clase: Class<*>, id: Int){
        val intent = Intent(this,clase)
        intent.putExtra("id",id)
        startActivity(intent)
    }
}