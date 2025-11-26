package com.example.tranquidescanso

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VerReservasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReservaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_reservas)

        recyclerView = findViewById(R.id.recyclerReservas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listaReservas = mutableListOf(
            ReservaItem(1, "Juan Pérez", "Agencia XYZ", "2025-11-26", "2025-11-28", 2),
            ReservaItem(2, "María Gómez", null, "2025-11-27", "2025-11-29", 1)
        )

        adapter = ReservaAdapter(listaReservas) { reserva ->
            Toast.makeText(
                this,
                "Detalle de ${reserva.huesped}, id: ${reserva.id}",
                Toast.LENGTH_SHORT
            ).show()
        }

        recyclerView.adapter = adapter
    }
}
