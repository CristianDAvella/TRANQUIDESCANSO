package com.example.tranquidescanso.ui.huespedes

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Huesped
import com.example.tranquidescanso.repository.HuespedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AgregarHuespedActivity : AppCompatActivity() {

    private val repository = HuespedRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_huesped)

        val etNombre = findViewById<EditText>(R.id.etNombreHuesped)
        val spinnerTipoDoc = findViewById<Spinner>(R.id.spinnerTipoDocumento)
        val etNumeroDoc = findViewById<EditText>(R.id.etNumeroDocumento)
        val llTelefonos = findViewById<LinearLayout>(R.id.llTelefonosContainer)
        val btnAgregarTelefono = findViewById<Button>(R.id.btnAgregarTelefono)
        val etDireccion = findViewById<EditText>(R.id.etCorreo) // LO USAMOS COMO DIRECCIÓN
        val btnGuardar = findViewById<Button>(R.id.btnGuardarHuesped)

        val tipos = listOf("CC", "TI", "RC", "Pasaporte")
        spinnerTipoDoc.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)

        btnAgregarTelefono.setOnClickListener {
            val nuevo = EditText(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { bottomMargin = 8 }

                hint = "Teléfono"
                setBackgroundResource(R.drawable.bg_naranja_claro)
                setPadding(12, 12, 12, 12)
            }
            llTelefonos.addView(nuevo)
        }

        btnAgregarTelefono.performClick()

        btnGuardar.setOnClickListener {

            val nombre = etNombre.text.toString()
            val tipoDoc = spinnerTipoDoc.selectedItem.toString()
            val numeroDoc = etNumeroDoc.text.toString()
            val direccion = etDireccion.text.toString() // este es tu campo "correo/dirección"

            val telefonos = mutableListOf<String>()
            for (i in 0 until llTelefonos.childCount) {
                val tel = (llTelefonos.getChildAt(i) as EditText).text.toString()
                if (tel.isNotEmpty()) telefonos.add(tel)
            }

            // Validación de campos
            if (nombre.isEmpty() || numeroDoc.isEmpty() || direccion.isEmpty() || telefonos.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear el objeto Huesped
            val nuevoHuesped = Huesped(
                nombre = nombre,
                tipoDocumento = tipoDoc,
                numeroDocumento = numeroDoc,
                correo = direccion,
                telefonos = telefonos
            )

            // Enviar a la API
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val resp = repository.crearHuesped(nuevoHuesped)

                    withContext(Dispatchers.Main) {
                        if (resp != null) {
                            Toast.makeText(this@AgregarHuespedActivity, "Huésped guardado", Toast.LENGTH_SHORT).show()
                            finish() // cerrar activity y volver a la lista
                        } else {
                            Toast.makeText(this@AgregarHuespedActivity, "Error al guardar", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AgregarHuespedActivity, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }



    }
}