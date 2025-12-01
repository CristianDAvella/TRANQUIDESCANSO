package com.example.tranquidescanso.ui.hotel

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Hotel

class HistorialHotelActivity : AppCompatActivity() {

    private lateinit var hotel: Hotel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial_hotel)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_historial_hotel)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        hotel = intent.getSerializableExtra("hotel") as Hotel
        val lvHistorial = findViewById<ListView>(R.id.lvHistorial)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, hotel.historialCategorias)
        lvHistorial.adapter = adapter
    }
}
