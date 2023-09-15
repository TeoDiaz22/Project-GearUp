package com.example.project_gearup

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.project_gearup.models.Motorcycle
import com.example.project_gearup.models.Review
import java.time.LocalDate

class CreateEditReview : AppCompatActivity() {

    var create:Boolean = true
    var idMotorcycle: String = ""
    lateinit var motorcycle: Motorcycle
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_edit_review)

        create = intent.getBooleanExtra("create", true)
        motorcycle = intent.getParcelableExtra("motorcycle", Motorcycle::class.java)!!
        idMotorcycle = intent.getStringExtra("idMotorcycle").toString()

        val title = findViewById<EditText>(R.id.et_title_review)
        val description = findViewById<EditText>(R.id.et_review)
        val buttonCreateEditReview = findViewById<TextView>(R.id.btn_create_edit_review)

        /*if (create) {
            buttonCreateEditReview.setOnClickListener {
                val review = Review(
                    (System.currentTimeMillis() % 10000).toString(),
                    "",
                    title.text.toString(),
                    description.text.toString(),
                )
                Database.listPayments.add(payment)
                createOrUpdate(payment, idClient)
            }
        }else{
            val payment = Database.listPayments[intent.getIntExtra("idItemSelected", 0)]
            title.text = "Editar el pago del mes: ${payment.month}"
            buttonCreateEditReview.text = "Actualizar"
            month.setSelection(resources.getStringArray(R.array.months).indexOf(payment.month))
            date.text = payment.date.toString()
            amount.setText(payment.amount.toString())
            inCash.isChecked = payment.inCash
            isLate.isChecked = payment.isLate

            buttonCreateEditReview.setOnClickListener {
                payment.month = month.selectedItem.toString()
                payment.date = LocalDate.parse(date.text.toString())
                payment.amount = amount.text.toString().toDouble()
                payment.inCash = inCash.isChecked
                payment.isLate = isLate.isChecked

                Database.listPayments[intent.getIntExtra("idItemSelected", 0)] = payment
                createOrUpdate(payment, idClient)
            }
        }*/
    }
}