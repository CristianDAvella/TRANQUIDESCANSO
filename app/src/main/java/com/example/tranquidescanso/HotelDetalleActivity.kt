package com.example.tranquidescanso

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HotelDetalleActivity : AppCompatActivity() {

    private var hotelId: Int = 0 // 0 indica que es nuevo hotel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detalle)

        val etNombre: EditText = findViewById(R.id.etNombreHotel)
        val etDireccion: EditText = findViewById(R.id.etDireccionHotel)
        val etTelefono: EditText = findViewById(R.id.etTelefonoHotel)
        val etAno: EditText = findViewById(R.id.etAnoHotel)
        val btnActualizar: Button = findViewById(R.id.btnActualizarCategoria)
        val spinner: Spinner = findViewById(R.id.spinnerCategoria)

        // Lista de categorías
        val categorias = listOf("1 estrella", "2 estrellas", "3 estrellas", "4 estrellas", "5 estrellas")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // ----- Revisar si vienen datos de un hotel existente -----
        hotelId = intent.getIntExtra("hotelId", 0)
        val nombre = intent.getStringExtra("hotelNombre") ?: ""
        val direccion = intent.getStringExtra("hotelDireccion") ?: ""
        val categoria = intent.getStringExtra("hotelCategoria") ?: ""

        if (hotelId != 0) {
            // Editar hotel existente → llenar campos
            etNombre.setText(nombre)
            etDireccion.setText(direccion)
            etTelefono.setText("") // se llenará desde BD
            etAno.setText("")
            val indexCategoria = categorias.indexOf(categoria)
            if (indexCategoria >= 0) spinner.setSelection(indexCategoria)

            btnActualizar.text = "Actualizar categoría"
        } else {
            // Nuevo hotel → campos vacíos
            etNombre.setText("")
            etDireccion.setText("")
            etTelefono.setText("")
            etAno.setText("")
            spinner.setSelection(0)
            btnActualizar.text = "Agregar hotel"
        }

        // ----- Acción del botón -----
        btnActualizar.setOnClickListener {
            val nombreHotel = etNombre.text.toString()
            val direccionHotel = etDireccion.text.toString()
            val telefonoHotel = etTelefono.text.toString()
            val anoHotel = etAno.text.toString()
            val nuevaCategoria = spinner.selectedItem.toString()

            if (nombreHotel.isBlank() || direccionHotel.isBlank()) {
                Toast.makeText(this, "Debe llenar nombre y dirección", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (hotelId == 0) {
                // Crear nuevo hotel
                Toast.makeText(
                    this,
                    "Nuevo hotel '$nombreHotel' agregado con categoría $nuevaCategoria",
                    Toast.LENGTH_SHORT
                ).show()
                // Aquí Cristian / Brandon conectarán a la BD para INSERT
            } else {
                // Actualizar hotel existente
                Toast.makeText(
                    this,
                    "Hotel ID $hotelId actualizado a categoría $nuevaCategoria",
                    Toast.LENGTH_SHORT
                ).show()
                // Aquí se conectará a la BD para UPDATE
            }

            finish() // cerrar pantalla y volver a lista
        }
    }
}
