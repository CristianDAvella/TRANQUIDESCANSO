package com.example.tranquidescanso.ui.estadistica

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.EstadisticasAdapter
import com.example.tranquidescanso.adapters.listaGlobalReservas
import com.example.tranquidescanso.model.Reserva
import java.util.*

class EstadisticasActivity : AppCompatActivity() {

    private lateinit var spinnerConsultas: Spinner
    private lateinit var etFechaInicio: EditText
    private lateinit var etFechaFin: EditText
    private lateinit var btnFiltrar: Button
    private lateinit var recyclerEstadisticas: RecyclerView
    private lateinit var adapter: EstadisticasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        spinnerConsultas = findViewById(R.id.spinnerConsultas)
        etFechaInicio = findViewById(R.id.etFechaInicio)
        etFechaFin = findViewById(R.id.etFechaFin)
        btnFiltrar = findViewById(R.id.btnFiltrar)
        recyclerEstadisticas = findViewById(R.id.recyclerEstadisticas)

        recyclerEstadisticas.layoutManager = LinearLayoutManager(this)

        val consultas = listOf(
            "Reservas realizadas en un período",
            "Reservas canceladas sin anticipo",
            "Reservas no utilizadas y pagaron anticipo",
            "Reservas llegada a tiempo",
            "Reservas con menores o mascotas",
            "Reservas con servicios adicionales",
            "Datos de huéspedes por reserva"
        )

        spinnerConsultas.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            consultas
        ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        btnFiltrar.setOnClickListener {
            filtrarEstadisticas()
        }
    }

    private fun filtrarEstadisticas() {
        val seleccion = spinnerConsultas.selectedItemPosition
        val resultados = mutableListOf<String>()

        val fechaInicioStr = etFechaInicio.text.toString()
        val fechaFinStr = etFechaFin.text.toString()

        fun String.toCalendar(): Calendar? {
            val parts = this.split("/")
            return if (parts.size == 3) {
                Calendar.getInstance().apply {
                    set(parts[2].toInt(), parts[1].toInt() - 1, parts[0].toInt())
                }
            } else null
        }

        val inicio = fechaInicioStr.toCalendar()
        val fin = fechaFinStr.toCalendar()

        fun dentroDeRango(fecha: String): Boolean {
            val f = fecha.toCalendar() ?: return false
            return if (inicio != null && fin != null) {
                !f.before(inicio) && !f.after(fin)
            } else true
        }

        when (seleccion) {
            0 -> { // Reservas realizadas en un período
                resultados.addAll(listaGlobalReservas.filter { dentroDeRango(it.fechaReserva) }
                    .map { "Reserva #${it.id} - ${it.huesped.nombre} - ${it.fechaReserva}" })
            }
            1 -> { // Canceladas sin pagar anticipo
                resultados.addAll(listaGlobalReservas.filter { it.estado == "Cancelada" && !it.anticipoPagado }
                    .map { "Reserva #${it.id} - ${it.huesped.nombre}" })
            }
            2 -> { // No utilizadas y pagaron anticipo
                resultados.addAll(listaGlobalReservas.filter { it.estado == "No utilizada" && it.anticipoPagado }
                    .map { "Reserva #${it.id} - ${it.huesped.nombre}" })
            }
            3 -> { // Llegada a tiempo
                resultados.addAll(listaGlobalReservas.filter { it.estado == "Llegada a tiempo" }
                    .map { "Reserva #${it.id} - ${it.huesped.nombre}" })
            }
            4 -> { // Menores o mascotas
                resultados.addAll(listaGlobalReservas.filter { r ->
                    r.huesped.esMenor || r.huesped.tieneMascota
                }.map { "Reserva #${it.id} - ${it.huesped.nombre}" })
            }
            5 -> { // Servicios adicionales
                resultados.addAll(listaGlobalReservas.filter { it.serviciosAsignados.isNotEmpty() }
                    .map { "Reserva #${it.id} - ${it.huesped.nombre} - Servicios: ${it.serviciosAsignados.joinToString { s -> s.nombre }}" })
            }
            6 -> { // Datos de huéspedes por reserva
                listaGlobalReservas.forEach { r ->
                    resultados.add("Reserva #${r.id} - Huésped: ${r.huesped.nombre}, Doc: ${r.huesped.numeroDocumento}, Tel: ${r.huesped.telefonos.joinToString()}")
                }
            }
            else -> resultados.add("Consulta no implementada")
        }

        adapter = EstadisticasAdapter(resultados)
        recyclerEstadisticas.adapter = adapter
    }
}
