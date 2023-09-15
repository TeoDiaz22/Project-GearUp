package com.example.project_gearup.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_gearup.Home
import com.example.project_gearup.R
import com.example.project_gearup.models.Motorcycle
import com.example.project_gearup.motocycle_info

class RVAdapterMotorcycle(
    private val context: Home,
    private val listMotorcycles: List<Motorcycle>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RVAdapterMotorcycle.MyViewHolder>() {

    inner class MyViewHolder (view: View): RecyclerView.ViewHolder(view){
        val imageMotorcycle: ImageView
        val model: TextView
        val price: TextView
        val motorcycleClass: TextView
        val cylinderCapacity: TextView
        val motor: TextView

        init {
            imageMotorcycle = view.findViewById(R.id.iv_image_motorcycle)
            model = view.findViewById(R.id.tv_model)
            price = view.findViewById(R.id.tv_price)
            motorcycleClass = view.findViewById(R.id.tv_class)
            cylinderCapacity = view.findViewById(R.id.tv_cyl_capacity)
            motor = view.findViewById(R.id.tv_motor)
            itemView.setOnClickListener {
                context.idItemSelected =  this.layoutPosition
                context.openActivityMotorcycle(motocycle_info::class.java)
            }

        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recycler_view_motorcycle,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RVAdapterMotorcycle.MyViewHolder, position: Int) {
        val actualMotorcycle = this.listMotorcycles[position]
        //holder.imageMotorcycle.setImageResource(actualMotorcycle.image)
        Glide.with(holder.imageMotorcycle.context).load(actualMotorcycle.image).into(holder.imageMotorcycle)
        holder.model.text = actualMotorcycle.model
        holder.price.text = actualMotorcycle.price.toString()
        holder.motorcycleClass.text = actualMotorcycle.motorcycleClass
        holder.cylinderCapacity.text = actualMotorcycle.cylinderCapacity.toString()
        holder.motor.text = actualMotorcycle.motor
    }

    override fun getItemCount(): Int {
        return this.listMotorcycles.size
    }

}