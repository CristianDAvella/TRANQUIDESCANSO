package com.example.tranquidescanso

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rvMenu)

        val menuItems = listOf(
            MenuItem("Hotel", R.drawable.hotel),
            MenuItem("Huéspedes", R.drawable.huesped),
            MenuItem("Reservas", R.drawable.reserva),
            MenuItem("Habitaciones", R.drawable.habitacion),
            MenuItem("Pagos/Servicios", R.drawable.pago),
            MenuItem("Estadísticas", R.drawable.estadistica)
        )

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val spacing = 16
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: android.view.View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(spacing, spacing, spacing, spacing)
            }
        })

        recyclerView.adapter = MenuAdapter(menuItems) { item ->
            when(item.nombre) {
                "Hotel" -> {
                    val intent = Intent(this, HotelActivity::class.java)
                    startActivity(intent)
                }
                "Huéspedes" -> {
                    val intent = Intent(this, HuespedActivity::class.java)
                    startActivity(intent)
                }
                "Reservas" -> {
                    val intent = Intent(this, MenuReservasActivity::class.java)
                    startActivity(intent)
                }
                "Habitaciones" -> {
                    // Abrir HabitacionActivity con id de ejemplo
                    val intent = Intent(this, HabitacionActivity::class.java)
                    intent.putExtra("idHabitacion", "HAB001")
                    startActivity(intent)
                }
                "Pagos/Servicios" -> {
                    Toast.makeText(this, "Pagos/Servicios pendiente", Toast.LENGTH_SHORT).show()
                }
                "Estadísticas" -> {
                    Toast.makeText(this, "Estadísticas pendiente", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
