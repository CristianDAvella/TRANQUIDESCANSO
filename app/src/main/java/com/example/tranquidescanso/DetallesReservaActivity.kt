package com.example.tranquidescanso

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetallesReservaActivity : AppCompatActivity() {

    private lateinit var tvHuesped: TextView
    private lateinit var tvAgencia: TextView
    private lateinit var tvFechas: TextView
    private lateinit var tvHabitaciones: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_reserva)

        // Inicializar vistas
        tvHuesped = findViewById(R.id.tvDetalleHuesped)
        tvAgencia = findViewById(R.id.tvDetalleAgencia)
        tvFechas = findViewById(R.id.tvDetalleFechas)
        tvHabitaciones = findViewById(R.id.tvDetalleHabitaciones)

        // Recibir datos de la reserva
        val huesped = intent.getStringExtra("huesped")
        val agencia = intent.getStringExtra("agencia")
        val fechaInicio = intent.getStringExtra("fechaInicio")
        val fechaFin = intent.getStringExtra("fechaFin")
        val habitaciones = intent.getIntExtra("habitaciones", 0)

        // Asignar valores a TextViews
        tvHuesped.text = "Huésped: $huesped"
        tvAgencia.text = "Agencia: ${agencia ?: "Ninguna"}"
        tvFechas.text = "Fechas: $fechaInicio → $fechaFin"
        tvHabitaciones.text = "Habitaciones: $habitaciones"
    }
}
