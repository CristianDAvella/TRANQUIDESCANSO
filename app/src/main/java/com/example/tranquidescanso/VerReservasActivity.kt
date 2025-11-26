package com.example.tranquidescanso

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VerReservasActivity : AppCompatActivity() {

    private lateinit var recyclerReservas: RecyclerView
    private lateinit var listaReservas: MutableList<ReservaItem>
    private lateinit var reservaAdapter: ReservaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_reservas)

        recyclerReservas = findViewById(R.id.recyclerReservas)

        // Datos de ejemplo
        listaReservas = mutableListOf(
            ReservaItem(1, "Juan Pérez", "Agencia X", "2025-12-01", "2025-12-05", 2),
            ReservaItem(2, "María López", null, "2025-12-10", "2025-12-12", 1)
        )

        // Adapter con los tres callbacks
        reservaAdapter = ReservaAdapter(
            listaReservas,
            onDetalleClick = { reserva ->
                val intent = Intent(this, DetallesReservaActivity::class.java)
                intent.putExtra("huesped", reserva.huesped)
                intent.putExtra("agencia", reserva.agencia)
                intent.putExtra("fechaInicio", reserva.fechaInicio)
                intent.putExtra("fechaFin", reserva.fechaFin)
                intent.putExtra("habitaciones", reserva.habitaciones)
                startActivity(intent)
            },
            onEditarClick = { reserva ->
                val intent = Intent(this, EditarReservaActivity::class.java)
                intent.putExtra("id", reserva.id)
                intent.putExtra("huesped", reserva.huesped)
                intent.putExtra("agencia", reserva.agencia)
                intent.putExtra("fechaInicio", reserva.fechaInicio)
                intent.putExtra("fechaFin", reserva.fechaFin)
                intent.putExtra("habitaciones", reserva.habitaciones)
                startActivityForResult(intent, 100)
            },
            onCancelarClick = { reserva ->
                val index = listaReservas.indexOfFirst { it.id == reserva.id }
                if (index != -1) {
                    listaReservas.removeAt(index)
                    reservaAdapter.notifyItemRemoved(index)
                }
            }
        )

        recyclerReservas.layoutManager = LinearLayoutManager(this)
        recyclerReservas.adapter = reservaAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            val id = data.getIntExtra("id", -1)
            val index = listaReservas.indexOfFirst { it.id == id }
            if (index != -1) {
                listaReservas[index] = listaReservas[index].copy(
                    huesped = data.getStringExtra("huesped") ?: "",
                    agencia = data.getStringExtra("agencia"),
                    fechaInicio = data.getStringExtra("fechaInicio") ?: "",
                    fechaFin = data.getStringExtra("fechaFin") ?: "",
                    habitaciones = data.getIntExtra("habitaciones", 1)
                )
                reservaAdapter.notifyItemChanged(index)
            }
        }
    }
}
