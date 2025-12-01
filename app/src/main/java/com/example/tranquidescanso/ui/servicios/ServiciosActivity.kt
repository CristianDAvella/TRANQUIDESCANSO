package com.example.tranquidescanso.ui.servicios

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.listaGlobalReservas
import com.example.tranquidescanso.model.Huesped
import com.example.tranquidescanso.model.Reserva
import com.example.tranquidescanso.model.Servicio
import com.example.tranquidescanso.model.Habitacion

class ServiciosActivity : AppCompatActivity() {

    private lateinit var spinnerReservas: Spinner
    private lateinit var spinnerServicios: Spinner
    private lateinit var btnAgregarServicio: Button
    private lateinit var listServiciosAsignados: ListView

    private var reservaSeleccionada: Reserva? = null

    // Lista de servicios de prueba
    private val listaServiciosDisponibles = listOf(
        Servicio(1, "Parqueadero", 10000.0),
        Servicio(2, "Desayuno adicional", 15000.0),
        Servicio(3, "Spa", 50000.0),
        Servicio(4, "Cama extra", 20000.0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)

        spinnerReservas = findViewById(R.id.spinnerReservas)
        spinnerServicios = findViewById(R.id.spinnerServicios)
        btnAgregarServicio = findViewById(R.id.btnAgregarServicio)
        listServiciosAsignados = findViewById(R.id.listServiciosAsignados)

        // --- Cargar reservas de prueba si listaGlobalReservas está vacía ---
        if (listaGlobalReservas.isEmpty()) {
            listaGlobalReservas.add(
                Reserva(
                    id = 1,
                    huesped = Huesped(1, "Juan Pérez", "CC", "123456", mutableListOf("300111222"), "juan@gmail.com"),
                    hotel = "Hotel Paraíso",
                    habitaciones = mutableListOf(
                        Habitacion(1, "101", "Doble", 2, 1, "Hotel Paraíso", "Disponible", "Habitación doble")
                    ),
                    agencia = "Agencia A",
                    fechaReserva = "01/12/2025",
                    fechaInicio = "05/12/2025",
                    fechaFin = "07/12/2025",
                    vencimiento = "19:00",
                    cantidadPersonas = 2,
                    anticipoPagado = true,
                    estado = "Confirmada"
                )
            )

            listaGlobalReservas.add(
                Reserva(
                    id = 2,
                    huesped = Huesped(2, "María López", "CC", "654321", mutableListOf("310999888"), "maria@gmail.com"),
                    hotel = "Hotel Central",
                    habitaciones = mutableListOf(
                        Habitacion(2, "201", "Suite", 3, 2, "Hotel Central", "Disponible", "Suite ejecutiva")
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
        }

        configurarSpinnerReservas()
        configurarSpinnerServicios()
        configurarBotonAgregar()
    }

    private fun configurarSpinnerReservas() {
        val nombresReservas = listaGlobalReservas.map { "Reserva #${it.id} - ${it.huesped.nombre}" }
        spinnerReservas.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresReservas)
            .also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        spinnerReservas.setSelection(0)
        reservaSeleccionada = listaGlobalReservas.firstOrNull()
        actualizarListaServiciosAsignados()

        spinnerReservas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                reservaSeleccionada = listaGlobalReservas[position]
                actualizarListaServiciosAsignados()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun configurarSpinnerServicios() {
        val nombresServicios = listaServiciosDisponibles.map { it.nombre }
        spinnerServicios.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresServicios)
            .also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
    }

    private fun configurarBotonAgregar() {
        btnAgregarServicio.setOnClickListener {
            val r = reservaSeleccionada ?: return@setOnClickListener
            val index = spinnerServicios.selectedItemPosition
            if (index != AdapterView.INVALID_POSITION) {
                val servicio = listaServiciosDisponibles[index]
                r.serviciosAsignados.add(servicio)
                Toast.makeText(this, "${servicio.nombre} agregado a la reserva", Toast.LENGTH_SHORT).show()
                actualizarListaServiciosAsignados()
            }
        }
    }

    private fun actualizarListaServiciosAsignados() {
        val r = reservaSeleccionada ?: return
        val listaNombres = r.serviciosAsignados.map { "${it.nombre} - $${it.costo}" }
        listServiciosAsignados.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaNombres)
    }
}
