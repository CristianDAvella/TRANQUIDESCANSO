package com.example.tranquidescanso

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.adapters.HabitacionAdapter
import com.example.tranquidescanso.model.HabitacionItem

class HabitacionActivity : AppCompatActivity() {

    private lateinit var btnCrearHabitacion: Button
    private lateinit var rvHabitaciones: RecyclerView
    private val listaHabitaciones = mutableListOf<HabitacionItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitacion)

        btnCrearHabitacion = findViewById(R.id.btnCrearHabitacion)
        rvHabitaciones = findViewById(R.id.rvHabitaciones)

        cargarDatosSimulados()

        val adapter = HabitacionAdapter(
            listaHabitaciones,

            onItemClick = { habitacion ->
                val intent = Intent(this, CrearHabitacionActivity::class.java)
                intent.putExtra("idHabitacion", habitacion.id)
                startActivity(intent)
            },

            onEditarClick = { habitacion ->
                val intent = Intent(this, CrearHabitacionActivity::class.java)
                intent.putExtra("idHabitacion", habitacion.id)
                startActivity(intent)
            },

            onReservarClick = { habitacion ->
                val intent = Intent(this, CrearReservaActivity::class.java)
                intent.putExtra("idHabitacion", habitacion.id)
                startActivity(intent)
            }
        )

        rvHabitaciones.layoutManager = LinearLayoutManager(this)
        rvHabitaciones.adapter = adapter

        btnCrearHabitacion.setOnClickListener {
            val intent = Intent(this, CrearHabitacionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun cargarDatosSimulados() {
        listaHabitaciones.add(
            HabitacionItem(
                "1", "101", "Sencilla", 2, 120.0,
                "Disponible", "Habitación con vista al mar",
                listOf("WiFi", "TV", "Baño privado")
            )
        )
        listaHabitaciones.add(
            HabitacionItem(
                "2", "102", "Doble", 4, 200.0,
                "Ocupada", "Habitación familiar",
                listOf("WiFi", "TV", "Aire acondicionado")
            )
        )
    }
}
