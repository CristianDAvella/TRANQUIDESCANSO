package com.example.tranquidescanso

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_reservas)

        findViewById<Button>(R.id.btnCrearReserva).setOnClickListener {
            startActivity(Intent(this, CrearReservaActivity::class.java))
        }

        findViewById<Button>(R.id.btnVerReservas).setOnClickListener {
            startActivity(Intent(this, VerReservasActivity::class.java))
        }

        findViewById<Button>(R.id.btnCrearAgencia).setOnClickListener {
            startActivity(Intent(this, CrearAgenciaActivity::class.java))
        }
    }
}
