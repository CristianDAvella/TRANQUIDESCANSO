package com.example.tranquidescanso

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tranquidescanso.model.HabitacionItem

class CrearHabitacionActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etTipo: EditText
    private lateinit var etCapacidad: EditText
    private lateinit var etPrecio: EditText
    private lateinit var etEstado: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_habitacion)

        etNombre = findViewById(R.id.etNombre)
        etTipo = findViewById(R.id.etTipo)
        etCapacidad = findViewById(R.id.etCapacidad)
        etPrecio = findViewById(R.id.etPrecio)
        etEstado = findViewById(R.id.etEstado)
        etDescripcion = findViewById(R.id.etDescripcion)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancelar)

        btnGuardar.setOnClickListener {
            val habitacion = HabitacionItem(
                id = System.currentTimeMillis().toString(),
                nombre = etNombre.text.toString(),
                tipo = etTipo.text.toString(),
                capacidad = etCapacidad.text.toString().toIntOrNull() ?: 0,
                precio = etPrecio.text.toString().toDoubleOrNull() ?: 0.0,
                estado = etEstado.text.toString(),
                descripcion = etDescripcion.text.toString(),
                servicios = listOf() // Puedes agregar checkboxes para servicios
            )
            Toast.makeText(this, "Habitación creada: ${habitacion.nombre}", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }
}
