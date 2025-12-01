package com.example.tranquidescanso.ui.reservas

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tranquidescanso.R

class ReservasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservas)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_reservas)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCrearReserva = findViewById<LinearLayout>(R.id.btnCrearReserva)
        val btnVerReservas = findViewById<LinearLayout>(R.id.btnVerReservas)
        val btnCheckIn = findViewById<LinearLayout>(R.id.btnCheckIn) // ← nuevo

        btnCrearReserva.setOnClickListener {
            startActivity(Intent(this, AgregarReservaActivity::class.java))
        }

        btnVerReservas.setOnClickListener {
            startActivity(Intent(this, VerReservasActivity::class.java))
        }

        btnCheckIn.setOnClickListener {
            startActivity(Intent(this, CheckInActivity::class.java))
        }
    }
}
