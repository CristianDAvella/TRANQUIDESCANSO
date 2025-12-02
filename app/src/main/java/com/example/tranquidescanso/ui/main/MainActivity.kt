package com.example.tranquidescanso.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.MenuAdapter
import com.example.tranquidescanso.model.MenuItem
import com.example.tranquidescanso.ui.hotel.HotelActivity
import com.example.tranquidescanso.ui.huesped.HuespedActivity
import com.example.tranquidescanso.ui.habitaciones.HabitacionActivity
import com.example.tranquidescanso.ui.agencias.AgenciaActivity
import com.example.tranquidescanso.ui.reservas.ReservasActivity
import com.example.tranquidescanso.ui.servicios.ServiciosActivity
import com.example.tranquidescanso.ui.estadistica.EstadisticasActivity


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

        // Adapter con acción al presionar cada item
        val adapter = MenuAdapter(menuItems) { item ->
            when (item.titulo.uppercase()) {
                "HOTEL" -> {
                    val intent = Intent(this, HotelActivity::class.java)
                    startActivity(intent)
                }
                "HUÉSPEDES" -> {
                    val intent = Intent(this, HuespedActivity::class.java)
                    startActivity(intent)
                }
                "RESERVAS" -> { // <--- Aquí redirigimos a Habitaciones
                    val intent = Intent(this, ReservasActivity::class.java)
                    startActivity(intent)
                }
                "HABITACIONES" -> { // <--- Aquí redirigimos a Habitaciones
                    val intent = Intent(this, HabitacionActivity::class.java)
                    startActivity(intent)
                }
                "AGENCIA"  -> { // <--- Aquí redirigimos a Habitaciones
                val intent = Intent(this, AgenciaActivity::class.java)
                startActivity(intent)
            }
                "SERVICIOS" -> { // <--- Aquí redirigimos a Habitaciones
                    val intent = Intent(this, ServiciosActivity::class.java)
                    startActivity(intent)
                }
                "ESTADÍSTICA" ->{ // <--- Aquí redirigimos a Habitaciones
                    val intent = Intent(this, EstadisticasActivity::class.java)
                    startActivity(intent)
                }
                "SALIR" -> {
                    finish() // Cierra la app
                }
            }
        }

        rvMenu.adapter = adapter
    }
}
