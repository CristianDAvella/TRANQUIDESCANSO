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

        // Lista corregida con todos los parámetros
        val hoteles = listOf(
            HotelItem(
                id = 1,
                nombre = "Hotel Paraíso",
                direccion = "Calle 123, Bogotá",
                telefono = "3001234567",
                ano = "1998",
                categoria = "4 estrellas"
            ),
            HotelItem(
                id = 2,
                nombre = "Hotel Central",
                direccion = "Carrera 45, Medellín",
                telefono = "3145678910",
                ano = "2005",
                categoria = "3 estrellas"
            ),
            HotelItem(
                id = 3,
                nombre = "Hotel Playa",
                direccion = "Avenida 7, Cartagena",
                telefono = "3209876543",
                ano = "2012",
                categoria = "5 estrellas"
            )
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HotelAdapter(hoteles)

        btnAgregarHotel.setOnClickListener {
            val intent = Intent(this, HotelDetalleActivity::class.java)
            intent.putExtra("hotelId", 0)
            startActivity(intent)
        }
    }
}
