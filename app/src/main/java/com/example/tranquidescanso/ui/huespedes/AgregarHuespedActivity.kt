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

        // Tipos de documento unificados
        val tipos = listOf("CC", "TI", "RC", "Pasaporte")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipoDoc.adapter = spinnerAdapter

        // Teléfono inicial
        btnAgregarTelefono.setOnClickListener {
            val nuevo = EditText(this)
            nuevo.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 8 }

            nuevo.setBackgroundResource(R.drawable.bg_naranja_claro)
            nuevo.setPadding(12, 12, 12, 12)
            nuevo.hint = "Teléfono"

            llTelefonos.addView(nuevo)
        }
        btnAgregarTelefono.performClick()

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val tipoDoc = spinnerTipoDoc.selectedItem.toString()
            val numeroDoc = etNumeroDoc.text.toString()
            val correo = etCorreo.text.toString()

            val telefonos = mutableListOf<String>()
            for (i in 0 until llTelefonos.childCount) {
                val tel = (llTelefonos.getChildAt(i) as EditText).text.toString()
                if (tel.isNotEmpty()) telefonos.add(tel)
            }

            if (nombre.isEmpty() || numeroDoc.isEmpty() || correo.isEmpty() || telefonos.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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
        }
    }
}
