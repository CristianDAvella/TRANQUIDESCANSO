package com.example.tranquidescanso

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HotelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)

        val recyclerView: RecyclerView = findViewById(R.id.rvHoteles)
        val btnAgregarHotel: Button = findViewById(R.id.btnAgregarHotel)

        // Lista de hoteles de ejemplo
        val hoteles = listOf(
            HotelItem(1, "Hotel Paraíso", "Calle 123, Bogotá", "4 estrellas"),
            HotelItem(2, "Hotel Central", "Carrera 45, Medellín", "3 estrellas"),
            HotelItem(3, "Hotel Playa", "Avenida 7, Cartagena", "5 estrellas")
        )

        // Configurar RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HotelAdapter(hoteles)

        // Botón agregar hotel → abrir detalle para agregar
        btnAgregarHotel.setOnClickListener {
            val intent = Intent(this, HotelDetalleActivity::class.java)
            intent.putExtra("hotelId", 0) // 0 = nuevo hotel
            startActivity(intent)
        }
    }
}
