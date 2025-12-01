package com.example.tranquidescanso.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.MenuAdapter
import com.example.tranquidescanso.model.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView del menú
        val rvMenu = findViewById<RecyclerView>(R.id.rvMenu)

        // Diseño en grid 2 columnas
        rvMenu.layoutManager = GridLayoutManager(this, 2)

        // Lista con los 8 botones del menú
        val menuItems = listOf(
            MenuItem("HOTEL", R.drawable.hotel),
            MenuItem("HUÉSPEDES", R.drawable.huesped),
            MenuItem("RESERVAS", R.drawable.reserva),
            MenuItem("HABITACIONES", R.drawable.habitacion),
            MenuItem("AGENCIA", R.drawable.agencia),
            MenuItem("SERVICIOS", R.drawable.servicio),
            MenuItem("ESTADÍSTICA", R.drawable.estadistica),
            MenuItem("SALIR", R.drawable.salir)
        )

        // Adapter
        val adapter = MenuAdapter(menuItems) { item ->
            // 👉 Aquí luego conectaremos la navegación a otras pantallas
        }

        rvMenu.adapter = adapter
    }
}
