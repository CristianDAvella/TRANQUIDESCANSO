package com.example.tranquidescanso.ui.habitaciones

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.HabitacionAdapter
import com.example.tranquidescanso.model.Habitacion

class HabitacionActivity : AppCompatActivity() {

    private lateinit var rvHabitaciones: RecyclerView
    private lateinit var adapter: HabitacionAdapter
    private val listaHabitaciones = mutableListOf<Habitacion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habitacion)

        rvHabitaciones = findViewById(R.id.rvHabitaciones)
        adapter = HabitacionAdapter(listaHabitaciones)
        rvHabitaciones.layoutManager = LinearLayoutManager(this)
        rvHabitaciones.adapter = adapter

        val btnAgregar: Button = findViewById(R.id.btnAgregarHabitacion)
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarHabitacionActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            // Recibiendo datos de la nueva habitación
            val numero = data.getStringExtra("numero") ?: return
            val tipo = data.getStringExtra("tipo") ?: return
            val capacidad = data.getIntExtra("capacidad", 1)
            val estado = data.getStringExtra("estado") ?: "Disponible"
            val hotelId = data.getIntExtra("hotelId", 0)
            val hotelNombre = data.getStringExtra("hotelNombre") ?: ""
            val descripcion = data.getStringExtra("descripcion") ?: ""

            val nuevaHabitacion = Habitacion(
                id = listaHabitaciones.size + 1,
                numero = numero,
                tipo = tipo,
                capacidad = capacidad,
                estado = estado,
                hotelId = hotelId,
                hotelNombre = hotelNombre,
                descripcion = descripcion
            )

            listaHabitaciones.add(nuevaHabitacion)
            adapter.notifyDataSetChanged()
        }
    }
}
