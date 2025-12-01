package com.example.tranquidescanso.ui.reservas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.ReservaAdapter
import com.example.tranquidescanso.adapters.listaGlobalReservas
import com.example.tranquidescanso.model.Habitacion
import com.example.tranquidescanso.model.Huesped
import com.example.tranquidescanso.model.Reserva

class VerReservasActivity : AppCompatActivity() {

    private lateinit var recyclerReservas: RecyclerView
    private lateinit var adapter: ReservaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_reservas)

        recyclerReservas = findViewById(R.id.recyclerReservas)
        recyclerReservas.layoutManager = LinearLayoutManager(this)

        cargarReservasMockIfEmpty()

        adapter = ReservaAdapter(listaGlobalReservas) { reserva, action ->
            if (action == "editar") {
                val intent = Intent(this, EditarReservaActivity::class.java)
                intent.putExtra("idReserva", reserva.id)
                startActivity(intent)
            }
        }

        recyclerReservas.adapter = adapter
    }

    // refresca al volver a la activity
    override fun onResume() {
        super.onResume()
        recyclerReservas.adapter?.notifyDataSetChanged()
    }

    private fun cargarReservasMockIfEmpty() {
        if (listaGlobalReservas.isNotEmpty()) return

        listaGlobalReservas.addAll(
            listOf(
                Reserva(
                    id = 1,
                    huesped = Huesped(
                        id = 0,
                        nombre = "Juan Pérez",
                        tipoDocumento = "CC",
                        numeroDocumento = "123456",
                        telefonos = mutableListOf("300111222"),
                        correo = "juan@gmail.com"
                    ),
                    hotel = "Hotel Paraíso",
                    habitacion = Habitacion(
                        id = 1,
                        numero = "101",
                        tipo = "Doble",
                        capacidad = 2,
                        hotelId = 1,
                        hotelNombre = "Hotel Paraíso",
                        estado = "Disponible",
                        descripcion = "Habitación doble"
                    ),
                    agencia = "Agencia A",
                    fechaReserva = "01/12/2025",
                    fechaInicio = "05/12/2025",
                    fechaFin = "07/12/2025",
                    vencimiento = "19:00",
                    cantidadPersonas = 2,
                    anticipoPagado = true,
                    estado = "Confirmada"
                ),
                Reserva(
                    id = 2,
                    huesped = Huesped(
                        id = 0,
                        nombre = "María López",
                        tipoDocumento = "CC",
                        numeroDocumento = "654321",
                        telefonos = mutableListOf("310999888"),
                        correo = "maria@gmail.com"
                    ),
                    hotel = "Hotel Central",
                    habitacion = Habitacion(
                        id = 3,
                        numero = "201",
                        tipo = "Suite",
                        capacidad = 4,
                        hotelId = 2,
                        hotelNombre = "Hotel Central",
                        estado = "Disponible",
                        descripcion = "Suite ejecutiva"
                    ),
                    agencia = null,
                    fechaReserva = "30/11/2025",
                    fechaInicio = "10/12/2025",
                    fechaFin = "12/12/2025",
                    vencimiento = "18:00",
                    cantidadPersonas = 3,
                    anticipoPagado = false,
                    estado = "Pendiente"
                )
            )
        )
    }
}
