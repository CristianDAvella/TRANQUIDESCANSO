package com.example.tranquidescanso.ui.habitaciones

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tranquidescanso.R

class AgregarHabitacionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_habitacion)

        val etNumero: EditText = findViewById(R.id.etNumero)
        val etCapacidad: EditText = findViewById(R.id.etCapacidad)
        val spinnerTipo: Spinner = findViewById(R.id.spinnerTipo)
        val spinnerEstado: Spinner = findViewById(R.id.spinnerEstado)
        val spinnerHotel: Spinner = findViewById(R.id.spinnerHotel)
        val btnGuardar: Button = findViewById(R.id.btnGuardarHabitacion)

        // Tipos de habitación
        val tipos = arrayOf("Sencilla", "Doble", "Suite", "Familiar")
        spinnerTipo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipos)

        // Estados
        val estados = arrayOf("Disponible", "Ocupada")
        spinnerEstado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estados)

        // Hoteles (se pueden cargar dinámicamente más adelante)
        val hoteles = arrayOf("Hotel Central", "Hotel Tranquilo")
        spinnerHotel.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hoteles)

        btnGuardar.setOnClickListener {
            val numero = etNumero.text.toString()
            val capacidad = etCapacidad.text.toString()

            if (numero.isEmpty() || capacidad.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent()
            intent.putExtra("numero", numero)
            intent.putExtra("tipo", spinnerTipo.selectedItem.toString())
            intent.putExtra("capacidad", capacidad.toInt())
            intent.putExtra("estado", spinnerEstado.selectedItem.toString())
            intent.putExtra("hotel", spinnerHotel.selectedItem.toString())

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
