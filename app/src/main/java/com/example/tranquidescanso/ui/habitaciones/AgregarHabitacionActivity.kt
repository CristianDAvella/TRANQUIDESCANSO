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

class AgregarHabitacionActivity : AppCompatActivity() {

    private val tiposHabitacion = listOf("Sencilla", "Doble", "Suite", "Familiar")
    private val hoteles = listOf("Hotel Paraíso", "Hotel Central", "Hotel Playa") // Simulación de tu lista real

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_habitacion)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_agregar_habitacion)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNumero = findViewById<EditText>(R.id.etNumero)
        val spinnerTipo = findViewById<Spinner>(R.id.spinnerTipo)
        val etCapacidad = findViewById<EditText>(R.id.etCapacidad)
        val spinnerHotel = findViewById<Spinner>(R.id.spinnerHotel)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarHabitacion)

        // Spinner Tipo
        val tipoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposHabitacion)
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipo.adapter = tipoAdapter

        // Spinner Hotel
        val hotelAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hoteles)
        hotelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerHotel.adapter = hotelAdapter

        btnGuardar.setOnClickListener {
            val numero = etNumero.text.toString().trim()
            val tipo = spinnerTipo.selectedItem.toString()
            val capacidad = etCapacidad.text.toString().trim().toIntOrNull() ?: 0
            val hotelNombre = spinnerHotel.selectedItem.toString()
            val hotelId = spinnerHotel.selectedItemPosition + 1
            val descripcion = etDescripcion.text.toString().trim()

            if(numero.isEmpty() || capacidad == 0 || descripcion.isEmpty()){
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val habitacion = Habitacion(
                numero = numero,
                tipo = tipo,
                capacidad = capacidad,
                hotelId = hotelId,
                descripcion = descripcion,
                hotelNombre = hotelNombre
            )

            val intent = Intent()
            intent.putExtra("habitacion", habitacion)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
