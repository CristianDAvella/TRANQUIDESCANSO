package com.example.tranquidescanso

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class Agencia(
    val nombre: String,
    val telefono: String,
    val direccion: String
)

class CrearAgenciaActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etDireccion: EditText
    private lateinit var btnGuardar: Button

    // Lista temporal de agencias (puedes reemplazar con base de datos)
    companion object {
        val listaAgencias = mutableListOf<Agencia>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_agencia)

        etNombre = findViewById(R.id.etNombreAgencia)
        etTelefono = findViewById(R.id.etTelefonoAgencia)
        etDireccion = findViewById(R.id.etDireccionAgencia)
        btnGuardar = findViewById(R.id.btnGuardarAgencia)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val direccion = etDireccion.text.toString().trim()

            if(nombre.isEmpty() || telefono.isEmpty() || direccion.isEmpty()){
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val nuevaAgencia = Agencia(nombre, telefono, direccion)
                listaAgencias.add(nuevaAgencia)
                Toast.makeText(this, "Agencia creada correctamente", Toast.LENGTH_SHORT).show()
                finish() // cerrar Activity
            }
        }
    }
}
