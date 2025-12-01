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

class EditarHuespedActivity : AppCompatActivity() {

    private lateinit var huesped: Huesped
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_huesped)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_editar_huesped)) { v, insets ->
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

        val tipos = listOf("CC", "TI", "CE", "Pasaporte")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipoDoc.adapter = spinnerAdapter

        // Obtener huesped del intent
        huesped = intent.getSerializableExtra("huesped") as Huesped
        position = intent.getIntExtra("position", -1)

        etNombre.setText(huesped.nombre)
        etNumeroDoc.setText(huesped.numeroDocumento)
        etCorreo.setText(huesped.correo)
        spinnerTipoDoc.setSelection(tipos.indexOf(huesped.tipoDocumento))

        // Cargar teléfonos
        huesped.telefonos.forEach { tel ->
            val et = EditText(this)
            et.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 8 }
            et.setBackgroundResource(R.drawable.bg_naranja_claro)
            et.setPadding(12,12,12,12)
            et.setText(tel)
            llTelefonos.addView(et)
        }

        btnAgregarTelefono.setOnClickListener {
            val nuevoEt = EditText(this)
            nuevoEt.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 8 }
            nuevoEt.setBackgroundResource(R.drawable.bg_naranja_claro)
            nuevoEt.setPadding(12,12,12,12)
            nuevoEt.hint = "Teléfono"
            llTelefonos.addView(nuevoEt)
        }

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
                huesped.nombre = nombre
                huesped.tipoDocumento = tipoDoc
                huesped.numeroDocumento = numeroDoc
                huesped.telefonos = telefonos
                huesped.correo = correo

                val intent = Intent()
                intent.putExtra("huesped_actualizado", huesped)
                intent.putExtra("position", position)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
