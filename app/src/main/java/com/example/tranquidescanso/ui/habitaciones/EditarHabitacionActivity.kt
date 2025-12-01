package com.example.tranquidescanso.ui.habitaciones

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Habitacion

class EditarHabitacionActivity : AppCompatActivity() {

    private val tiposHabitacion = listOf("Sencilla", "Doble", "Suite", "Familiar")
    private val hoteles = listOf("Hotel Paraíso", "Hotel Central", "Hotel Playa")
    private val estados = listOf("Disponible", "Ocupada")

    private lateinit var habitacion: Habitacion
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_habitacion)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_editar_habitacion)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNumero = findViewById<EditText>(R.id.etNumero)
        val spinnerTipo = findViewById<Spinner>(R.id.spinnerTipo)
        val etCapacidad = findViewById<EditText>(R.id.etCapacidad)
        val spinnerHotel = findViewById<Spinner>(R.id.spinnerHotel)
        val spinnerEstado = findViewById<Spinner>(R.id.spinnerEstado)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarHabitacion)

        spinnerTipo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposHabitacion).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinnerHotel.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hoteles).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinnerEstado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        habitacion = intent.getSerializableExtra("habitacion") as Habitacion
        position = intent.getIntExtra("position", -1)

        etNumero.setText(habitacion.numero)
        etCapacidad.setText(habitacion.capacidad.toString())
        etDescripcion.setText(habitacion.descripcion)
        spinnerTipo.setSelection(tiposHabitacion.indexOf(habitacion.tipo))
        spinnerHotel.setSelection(habitacion.hotelId - 1)
        spinnerEstado.setSelection(estados.indexOf(habitacion.estado))

        btnGuardar.setOnClickListener {
            val numero = etNumero.text.toString().trim()
            val tipo = spinnerTipo.selectedItem.toString()
            val capacidad = etCapacidad.text.toString().trim().toIntOrNull() ?: 0
            val hotelNombre = spinnerHotel.selectedItem.toString()
            val hotelId = spinnerHotel.selectedItemPosition + 1
            val estado = spinnerEstado.selectedItem.toString()
            val descripcion = etDescripcion.text.toString().trim()

            if(numero.isEmpty() || capacidad == 0 || descripcion.isEmpty()){
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            habitacion.numero = numero
            habitacion.tipo = tipo
            habitacion.capacidad = capacidad
            habitacion.hotelId = hotelId
            habitacion.hotelNombre = hotelNombre
            habitacion.estado = estado
            habitacion.descripcion = descripcion

            val intent = Intent()
            intent.putExtra("habitacion", habitacion)
            intent.putExtra("position", position)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}


