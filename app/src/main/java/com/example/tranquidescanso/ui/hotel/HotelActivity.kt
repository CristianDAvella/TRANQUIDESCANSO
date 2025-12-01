package com.example.tranquidescanso.ui.hotel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.HotelAdapter
import com.example.tranquidescanso.model.Hotel

class HotelActivity : AppCompatActivity() {

    private val hoteles = mutableListOf<Hotel>()
    private lateinit var hotelAdapter: HotelAdapter
    private val AGREGAR_HOTEL_REQUEST = 1001
    private val EDITAR_HOTEL_REQUEST = 1002

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hotel)

        // Edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_hotel)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAgregarHotel = findViewById<Button>(R.id.btnAgregarHotel)
        val rvHoteles = findViewById<RecyclerView>(R.id.rvHoteles)

        // Lista de prueba
        hoteles.addAll(
            listOf(
                Hotel("Hotel Paraíso", "Calle 123, Bogotá", mutableListOf("3001234567"), "1998", "4 estrellas"),
                Hotel("Hotel Central", "Carrera 45, Medellín", mutableListOf("3145678910"), "2005", "3 estrellas"),
                Hotel("Hotel Playa", "Avenida 7, Cartagena", mutableListOf("3209876543"), "2012", "5 estrellas")
            )
        )

        // RecyclerView
        hotelAdapter = HotelAdapter(hoteles) { hotel, action ->
            when(action) {
                "editar" -> {
                    val intent = Intent(this, EditarHotelActivity::class.java)
                    intent.putExtra("hotel", hotel)
                    val position = hoteles.indexOf(hotel)
                    intent.putExtra("position", position)
                    startActivityForResult(intent, EDITAR_HOTEL_REQUEST)
                }
                "historial" -> {
                    val intent = Intent(this, HistorialHotelActivity::class.java)
                    intent.putExtra("hotel", hotel)
                    startActivity(intent)
                }

            }
        }
        rvHoteles.layoutManager = LinearLayoutManager(this)
        rvHoteles.adapter = hotelAdapter

        // Agregar hotel
        btnAgregarHotel.setOnClickListener {
            val intent = Intent(this, AgregarHotelActivity::class.java)
            startActivityForResult(intent, AGREGAR_HOTEL_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && data != null) {
            when(requestCode) {
                AGREGAR_HOTEL_REQUEST -> {
                    val hotel = data.getSerializableExtra("hotel") as Hotel
                    hoteles.add(hotel)
                    hotelAdapter.notifyItemInserted(hoteles.size - 1)
                }
                EDITAR_HOTEL_REQUEST -> {
                    val hotelActualizado = data.getSerializableExtra("hotel_actualizado") as Hotel
                    val position = data.getIntExtra("position", -1)
                    if(position != -1) {
                        hoteles[position] = hotelActualizado
                        hotelAdapter.notifyItemChanged(position)
                    }
                }
            }
        }
    }
}
