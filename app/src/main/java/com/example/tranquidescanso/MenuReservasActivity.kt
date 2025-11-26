package com.example.tranquidescanso

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_reservas)

        // Botón Crear Reserva
        findViewById<Button>(R.id.btnCrearReserva).setOnClickListener {
            startActivity(Intent(this, CrearReservaActivity::class.java))
        }

        // Botón Ver Reservas
        findViewById<Button>(R.id.btnVerReservas).setOnClickListener {
            startActivity(Intent(this, VerReservasActivity::class.java))
        }

        // Botón Editar Reserva
        findViewById<Button>(R.id.btnEditarReserva).setOnClickListener {
            startActivity(Intent(this, EditarReservaActivity::class.java))
        }

        // Botón Cancelar Reserva
        findViewById<Button>(R.id.btnCancelarReserva).setOnClickListener {
            startActivity(Intent(this, CancelarReservaActivity::class.java))
        }

        // Botón Ver Detalles de Reserva
        findViewById<Button>(R.id.btnDetallesReserva).setOnClickListener {
            startActivity(Intent(this, DetallesReservaActivity::class.java))
        }

        // Botón Crear Agencia
        findViewById<Button>(R.id.btnCrearAgencia).setOnClickListener {
            startActivity(Intent(this, CrearAgenciaActivity::class.java))
        }
    }
}
