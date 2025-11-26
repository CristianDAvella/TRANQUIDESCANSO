package com.example.tranquidescanso

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class HuespedDetalleActivity : AppCompatActivity() {

    private var huespedId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huesped_detalle)

        val spTipoDocumento: Spinner = findViewById(R.id.spDetalleTipoDocumento)
        val etDocumento: EditText = findViewById(R.id.etDetalleDocumento)
        val etNombre: EditText = findViewById(R.id.etDetalleNombre)
        val etTelefono: EditText = findViewById(R.id.etDetalleTelefono)
        val etEmail: EditText = findViewById(R.id.etDetalleEmail)
        val btnGuardar: Button = findViewById(R.id.btnGuardarHuesped)

        val tiposDocumento = listOf("Cédula", "Tarjeta de Identidad", "Registro Civil", "Pasaporte")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposDocumento)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTipoDocumento.adapter = adapter

        huespedId = intent.getIntExtra("huespedId", -1)
        etNombre.setText(intent.getStringExtra("huespedNombre") ?: "")
        etDocumento.setText(intent.getStringExtra("huespedDocumento") ?: "")
        etTelefono.setText(intent.getStringExtra("huespedTelefono") ?: "")
        etEmail.setText(intent.getStringExtra("huespedEmail") ?: "")

        val tipoActual = intent.getStringExtra("huespedTipoDocumento")
        tipoActual?.let {
            val pos = tiposDocumento.indexOf(it)
            if (pos >= 0) spTipoDocumento.setSelection(pos)
        }

        btnGuardar.setOnClickListener {
            val data = Intent()
            data.putExtra("huespedId", huespedId)
            data.putExtra("huespedNombre", etNombre.text.toString())
            data.putExtra("huespedDocumento", etDocumento.text.toString())
            data.putExtra("huespedTelefono", etTelefono.text.toString())
            data.putExtra("huespedEmail", etEmail.text.toString())
            data.putExtra("huespedTipoDocumento", spTipoDocumento.selectedItem.toString())
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }
}
