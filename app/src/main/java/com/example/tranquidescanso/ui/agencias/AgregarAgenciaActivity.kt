package com.example.tranquidescanso.ui.agencias

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Agencia

class AgregarAgenciaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_agencia)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_agregar_agencia)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre = findViewById<EditText>(R.id.etNombreAgencia)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarAgencia)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            if(nombre.isEmpty()){
                Toast.makeText(this, "Ingresa el nombre de la agencia", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val agencia = Agencia(nombre = nombre)
            val intent = Intent()
            intent.putExtra("agencia", agencia)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}

