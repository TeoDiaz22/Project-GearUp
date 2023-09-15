package com.example.project_gearup

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.project_gearup.models.Motorcycle
import com.example.project_gearup.models.Review
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class ReviewView : AppCompatActivity() {

    var idItemSelected = 0
    var idMotorcycle = ""
    lateinit var motorcycle: Motorcycle
    lateinit var listViewMotorcycles: ListView
    lateinit var adapter : ArrayAdapter<Review>

    val callback = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result ->
        if(result.resultCode == Activity.RESULT_OK){
            if(result.data != null){
                //Lógica negocio
                val data = result.data
                //showSnackbar("${data?.getStringExtra("message")}")
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        //var client = DataBase.list[intent.getIntExtra("idItemSelected", 0)]
        motorcycle = intent.getParcelableExtra("motorcycle",Motorcycle::class.java)!!
        idMotorcycle = intent.getStringExtra("motorcycleId").toString()

        val buttonAdd = findViewById<Button>(R.id.btn_add_review)
        buttonAdd.setOnClickListener {
            openActivityCreateEdit(CreateEditReview::class.java, true)
        }

        //Adapter for Clients ListView
        listViewMotorcycles = findViewById<ListView>(R.id.lv_reviews)
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            DataBase.listReviews
        )
        listViewMotorcycles.adapter = adapter
        adapter.notifyDataSetChanged()

        registerForContextMenu(listViewMotorcycles)
    }

    override fun onResume() {
        super.onResume()
        getAllByMotorcycle(idMotorcycle.toString())
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_review, menu)
        //Get ID of selected client
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idItemSelected = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_review_edit ->{
                openActivityCreateEdit(CreateEditReview::class.java, false)
                return true
            }
            R.id.mi_review_delete ->{
                deleteDialog()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun openActivityCreateEdit(
        classToOpen: Class<*>,
        create: Boolean
    ){
        val intent = Intent(this, classToOpen)
        intent.putExtra("create", create)
        intent.putExtra("idItemSelected", idItemSelected)
        intent.putExtra("motorcycle", motorcycle)
        callback.launch(intent)
        //startActivity(intent)
    }

    fun deleteDialog(){
        val builder = AlertDialog.Builder(this)
        val review = DataBase.listReviews[idItemSelected]
        builder.setTitle("¿Desea eliminar el cliente ${review.title}?")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            val review = DataBase.listReviews.removeAt(idItemSelected)
            adapter.notifyDataSetChanged()
            delete(review.id!!, idMotorcycle.toString())
        }
        builder.setNegativeButton("Cancelar", null)
        val dialog = builder.create()
        dialog.show()
    }

    fun getAllByMotorcycle(refMotorcycle: String){
        val db = Firebase.firestore
        val reviews = db.collection("motorcycles").document(refMotorcycle).collection("reviews")
        DataBase.listReviews.clear()
        reviews.get()
            .addOnSuccessListener { result ->
                for (queryDocumentSnapshot in result){
                    val review = Review(
                        queryDocumentSnapshot.data["id"].toString().toInt(),
                        queryDocumentSnapshot.data["user"].toString(),
                        queryDocumentSnapshot.data["title"].toString(),
                        queryDocumentSnapshot.data["review"].toString()
                    )
                    DataBase.listReviews.add(review)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {  }
    }

    fun delete(id: Int, refMotorcycle: String){
        val db = Firebase.firestore
        val payments = db.collection("motorcycles").document(refMotorcycle).collection("reviews")
        payments.document(id.toString())
            .delete()
    }


}