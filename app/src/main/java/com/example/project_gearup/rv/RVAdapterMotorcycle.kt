package com.example.project_gearup.rv

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_gearup.MainActivity
import com.example.project_gearup.models.Motorcycle

class RVAdapterMotorcycle(
    private val context: MainActivity,
    private val listaRestaurant: List<Motorcycle>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RVAdapterMotorcycle.MyViewHolder>() {

    inner class MyViewHolder (view: View): RecyclerView.ViewHolder(view){

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVAdapterMotorcycle.MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RVAdapterMotorcycle.MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}