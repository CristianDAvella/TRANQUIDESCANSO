package com.example.tranquidescanso

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HotelDetalleActivity : AppCompatActivity() {

    private var hotelId: Int = 0 // 0 = nuevo hotel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detalle)

        // Inputs
        val etNombre: EditText = findViewById(R.id.etNombreHotel)
        val etDireccion: EditText = findViewById(R.id.etDireccionHotel)
        val etTelefono: EditText = findViewById(R.id.etTelefonoHotel)
        val etAno: EditText = findViewById(R.id.etAnoHotel)
        val spinner: Spinner = findViewById(R.id.spinnerCategoria)
        val btnGuardar: Button = findViewById(R.id.btnActualizarCategoria)

        // Categorías
        val categorias = listOf("1 estrella", "2 estrellas", "3 estrellas", "4 estrellas", "5 estrellas")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Recibir datos desde el Adapter
        hotelId = intent.getIntExtra("hotelId", 0)
        val nombre = intent.getStringExtra("hotelNombre") ?: ""
        val direccion = intent.getStringExtra("hotelDireccion") ?: ""
        val telefono = intent.getStringExtra("hotelTelefono") ?: ""
        val ano = intent.getStringExtra("hotelAno") ?: ""
        val categoria = intent.getStringExtra("hotelCategoria") ?: ""

        if (hotelId != 0) {
            // Editar hotel existente
            etNombre.setText(nombre)
            etDireccion.setText(direccion)
            etTelefono.setText(telefono)
            etAno.setText(ano)

            val indexCategoria = categorias.indexOf(categoria)
            if (indexCategoria >= 0) spinner.setSelection(indexCategoria)

            btnGuardar.text = "Actualizar hotel"

        } else {
            // Nuevo hotel
            etNombre.setText("")
            etDireccion.setText("")
            etTelefono.setText("")
            etAno.setText("")
            spinner.setSelection(0)

            btnGuardar.text = "Agregar hotel"
        }

        btnGuardar.setOnClickListener {
            val nombreHotel = etNombre.text.toString()
            val direccionHotel = etDireccion.text.toString()
            val telefonoHotel = etTelefono.text.toString()
            val anoHotel = etAno.text.toString()
            val categoriaSeleccionada = spinner.selectedItem.toString()

            // Validaciones básicas
            if (nombreHotel.isBlank() || direccionHotel.isBlank() || telefonoHotel.isBlank() || anoHotel.isBlank()) {
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (hotelId == 0) {
                // Nuevo hotel
                Toast.makeText(
                    this,
                    "Nuevo hotel agregado: $nombreHotel ($categoriaSeleccionada)",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                // Actualizar hotel
                Toast.makeText(
                    this,
                    "Hotel ID $hotelId actualizado correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            }

            finish()
        }
    }
}
