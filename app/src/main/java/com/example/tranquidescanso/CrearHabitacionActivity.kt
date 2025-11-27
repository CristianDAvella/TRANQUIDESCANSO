package com.example.tranquidescanso

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.tranquidescanso.model.HabitacionItem

class CrearHabitacionActivity : AppCompatActivity() {

    private lateinit var etNumero: EditText
    private lateinit var etCapacidad: EditText
    private lateinit var etPrecio: EditText
    private lateinit var spinnerTipo: Spinner
    private lateinit var spinnerEstado: Spinner
    private lateinit var btnGuardar: Button

    // Simulación de lista de habitaciones (temporal)
    private val listaHabitaciones = mutableListOf<HabitacionItem>()

    private var habitacionEdit: HabitacionItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_habitacion)

        etNumero = findViewById(R.id.etNumeroHabitacion)
        etCapacidad = findViewById(R.id.etCapacidadHabitacion)
        etPrecio = findViewById(R.id.etPrecioHabitacion)
        spinnerTipo = findViewById(R.id.spinnerTipo)
        spinnerEstado = findViewById(R.id.spinnerEstado)
        btnGuardar = findViewById(R.id.btnGuardarHabitacion)

        // Spinner Tipo
        val tipos = listOf("Sencilla", "Doble", "Suite")
        spinnerTipo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipos)

        // Spinner Estado
        val estados = listOf("Disponible", "Ocupada", "Mantenimiento")
        spinnerEstado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, estados)

        // Revisar si viene un id para editar
        val idHabitacion = intent.getStringExtra("idHabitacion")
        if (idHabitacion != null) {
            habitacionEdit = listaHabitaciones.find { it.id == idHabitacion }
            habitacionEdit?.let { h ->
                etNumero.setText(h.nombre)
                etCapacidad.setText(h.capacidad.toString())
                etPrecio.setText(h.precio.toString())
                spinnerTipo.setSelection(tipos.indexOf(h.tipo))
                spinnerEstado.setSelection(estados.indexOf(h.estado))
            }
        }

        btnGuardar.setOnClickListener {
            val numero = etNumero.text.toString()
            val capacidad = etCapacidad.text.toString().toIntOrNull() ?: 0
            val precio = etPrecio.text.toString().toDoubleOrNull() ?: 0.0
            val tipo = spinnerTipo.selectedItem.toString()
            val estado = spinnerEstado.selectedItem.toString()

            if (habitacionEdit != null) {
                // Editar
                habitacionEdit?.apply {
                    nombre = numero
                    this.capacidad = capacidad
                    this.precio = precio
                    this.tipo = tipo
                    this.estado = estado
                }
            } else {
                // Crear nueva
                val nueva = HabitacionItem(
                    id = System.currentTimeMillis().toString(),
                    nombre = numero,
                    tipo = tipo,
                    capacidad = capacidad,
                    precio = precio,
                    estado = estado
                )
                listaHabitaciones.add(nueva)
            }

            finish() // Cierra la actividad y vuelve a Habitaciones
        }
    }
}
