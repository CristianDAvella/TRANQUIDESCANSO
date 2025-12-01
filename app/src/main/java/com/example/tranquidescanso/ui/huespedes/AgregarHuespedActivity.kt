package com.example.tranquidescanso.ui.huespedes

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Huesped

class AgregarHuespedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_huesped)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_agregar_huesped)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre = findViewById<EditText>(R.id.etNombreHuesped)
        val spinnerTipoDoc = findViewById<Spinner>(R.id.spinnerTipoDocumento)
        val etNumeroDoc = findViewById<EditText>(R.id.etNumeroDocumento)
        val llTelefonos = findViewById<LinearLayout>(R.id.llTelefonosContainer)
        val btnAgregarTelefono = findViewById<Button>(R.id.btnAgregarTelefono)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarHuesped)

        // Spinner tipo de documento
        val tipos = listOf("Cédula", "Tarjeta de Identidad", "Registro Civil", "Pasaporte")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipoDoc.adapter = spinnerAdapter

        // Teléfonos dinámicos
        btnAgregarTelefono.setOnClickListener {
            val nuevoEt = EditText(this)
            nuevoEt.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 8 }
            nuevoEt.setBackgroundResource(R.drawable.bg_naranja_claro)
            nuevoEt.setPadding(12, 12, 12, 12)
            nuevoEt.hint = "Teléfono"
            llTelefonos.addView(nuevoEt)
        }

        // Primer campo de teléfono por defecto
        btnAgregarTelefono.performClick()

        // Guardar huesped
        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val tipoDoc = spinnerTipoDoc.selectedItem.toString()
            val numeroDoc = etNumeroDoc.text.toString().trim()
            val telefonos = mutableListOf<String>()
            for(i in 0 until llTelefonos.childCount) {
                val et = llTelefonos.getChildAt(i) as EditText
                val tel = et.text.toString().trim()
                if(tel.isNotEmpty()) telefonos.add(tel)
            }
            val correo = etCorreo.text.toString().trim()

            if(nombre.isNotEmpty() && numeroDoc.isNotEmpty() && telefonos.isNotEmpty() && correo.isNotEmpty()){
                val huesped = Huesped(
                    id = 0,
                    nombre = nombre,
                    tipoDocumento = tipoDoc,
                    numeroDocumento = numeroDoc,
                    telefonos = telefonos,
                    correo = correo
                )
                val intent = Intent()
                intent.putExtra("huesped", huesped)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
