package com.example.tranquidescanso.ui.hotel

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Hotel
import java.util.*

class AgregarHotelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_hotel)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_agregar_hotel)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre = findViewById<EditText>(R.id.etNombreHotel)
        val etDireccion = findViewById<EditText>(R.id.etDireccionHotel)
        val llTelefonos = findViewById<LinearLayout>(R.id.llTelefonosContainer)
        val btnAgregarTelefono = findViewById<Button>(R.id.btnAgregarTelefono)
        val etFecha = findViewById<EditText>(R.id.etFechaInauguracion)
        val spinnerCategoria = findViewById<Spinner>(R.id.spinnerCategoria)
        val btnAgregarHotel = findViewById<Button>(R.id.btnAgregarHotel)

        val categorias = listOf("1 estrella", "2 estrellas", "3 estrellas", "4 estrellas", "5 estrellas")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategoria.adapter = spinnerAdapter

        btnAgregarTelefono.setOnClickListener {
            val nuevoEt = EditText(this)
            nuevoEt.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 8 }
            nuevoEt.hint = "Teléfono"
            nuevoEt.setBackgroundResource(R.drawable.bg_naranja_claro)
            nuevoEt.setPadding(12,12,12,12)
            llTelefonos.addView(nuevoEt)
        }
        btnAgregarTelefono.performClick()

        etFecha.setOnClickListener {
            val cal = Calendar.getInstance()
            val dpd = DatePickerDialog(this, R.style.DatePickerNaranja, { _, y, m, d ->
                etFecha.setText("%02d/%02d/%04d".format(d, m+1, y))
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            dpd.show()
        }

        btnAgregarHotel.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val direccion = etDireccion.text.toString().trim()
            val telefonos = mutableListOf<String>()
            for(i in 0 until llTelefonos.childCount){
                val et = llTelefonos.getChildAt(i) as EditText
                val tel = et.text.toString().trim()
                if(tel.isNotEmpty()) telefonos.add(tel)
            }
            val fecha = etFecha.text.toString().trim()
            val categoria = spinnerCategoria.selectedItem.toString()

            if(nombre.isNotEmpty() && direccion.isNotEmpty() && telefonos.isNotEmpty() && fecha.isNotEmpty()) {
                val hotel = Hotel(nombre, direccion, telefonos, fecha, categoria)
                val intent = Intent()
                intent.putExtra("hotel", hotel)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
