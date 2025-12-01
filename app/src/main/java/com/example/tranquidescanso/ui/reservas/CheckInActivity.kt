package com.example.tranquidescanso.ui.reservas

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.HabitacionCheckInAdapter
import com.example.tranquidescanso.adapters.listaGlobalReservas
import com.example.tranquidescanso.model.Habitacion
import com.example.tranquidescanso.model.Huesped
import com.example.tranquidescanso.model.HuespedCheckIn
import com.example.tranquidescanso.model.Reserva
import java.util.*

class CheckInActivity : AppCompatActivity() {

    private lateinit var spinnerReservas: Spinner
    private lateinit var etFechaCheckIn: EditText
    private lateinit var etFechaCheckOut: EditText
    private lateinit var recyclerHabitaciones: RecyclerView
    private lateinit var btnGuardarCheckIn: Button

    private var reservaSeleccionada: Reserva? = null
    private var adapter: HabitacionCheckInAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_in)

        // --- BIND VIEWS ---
        spinnerReservas = findViewById(R.id.spinnerReservas)
        etFechaCheckIn = findViewById(R.id.etFechaCheckIn)
        etFechaCheckOut = findViewById(R.id.etFechaCheckOut)
        recyclerHabitaciones = findViewById(R.id.recyclerHabitaciones)
        btnGuardarCheckIn = findViewById(R.id.btnGuardarCheckIn)

        // --- CARGA DE RESERVAS DE PRUEBA ---
        cargarReservasMock()

        // --- SPINNER RESERVAS ---
        spinnerReservas.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listaGlobalReservas.map { "Reserva #${it.id} - ${it.huesped.nombre}" }
        ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        spinnerReservas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                reservaSeleccionada = listaGlobalReservas[position]
                cargarHabitaciones()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // --- DATE PICKERS ---
        etFechaCheckIn.setOnClickListener { mostrarDatePicker(etFechaCheckIn) }
        etFechaCheckOut.setOnClickListener { mostrarDatePicker(etFechaCheckOut) }

        // --- BOTÓN GUARDAR ---
        btnGuardarCheckIn.setOnClickListener {
            Toast.makeText(this, "Check-in registrado exitosamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarReservasMock() {
        if (listaGlobalReservas.isEmpty()) {
            // Reserva de Juan Pérez
            listaGlobalReservas.add(
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
                    habitaciones = mutableListOf(
                        Habitacion(
                            id = 1,
                            numero = "101",
                            tipo = "Doble",
                            capacidad = 2,
                            hotelId = 1,
                            hotelNombre = "Hotel Paraíso",
                            estado = "Disponible",
                            descripcion = "Habitación doble"
                        )
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

            // Reserva de María López con 2 habitaciones y 5 personas
            val huespedesMaria = listOf(
                Huesped(1, "María López", "CC", "654321", mutableListOf("310999888"), "maria@gmail.com"),
                Huesped(2, "Pedro López", "CC", "654322", mutableListOf("310999889"), "pedro@gmail.com"),
                Huesped(3, "Ana López", "CC", "654323", mutableListOf("310999890"), "ana@gmail.com"),
                Huesped(4, "Luis López", "CC", "654324", mutableListOf("310999891"), "luis@gmail.com"),
                Huesped(5, "Sofía López", "CC", "654325", mutableListOf("310999892"), "sofia@gmail.com")
            )

            val reservaMaria = Reserva(
                id = 2,
                huesped = huespedesMaria[0],
                hotel = "Hotel Central",
                habitaciones = mutableListOf(
                    Habitacion(
                        id = 2,
                        numero = "201",
                        tipo = "Suite",
                        capacidad = 3,
                        hotelId = 2,
                        hotelNombre = "Hotel Central",
                        estado = "Disponible",
                        descripcion = "Suite ejecutiva"
                    ),
                    Habitacion(
                        id = 3,
                        numero = "202",
                        tipo = "Doble",
                        capacidad = 2,
                        hotelId = 2,
                        hotelNombre = "Hotel Central",
                        estado = "Disponible",
                        descripcion = "Habitación doble"
                    )
                ),
                agencia = null,
                fechaReserva = "30/11/2025",
                fechaInicio = "10/12/2025",
                fechaFin = "12/12/2025",
                vencimiento = "18:00",
                cantidadPersonas = 5,
                anticipoPagado = false,
                estado = "Pendiente"
            )

            // Asignar huéspedes a las habitaciones
            reservaMaria.habitaciones[0].huespedesCheckIn.addAll(
                huespedesMaria.subList(0, 3).map { HuespedCheckIn(it) }
            )
            reservaMaria.habitaciones[1].huespedesCheckIn.addAll(
                huespedesMaria.subList(3, 5).map { HuespedCheckIn(it) }
            )

            listaGlobalReservas.add(reservaMaria)
        }
    }

    private fun cargarHabitaciones() {
        val habitaciones = reservaSeleccionada?.habitaciones ?: return
        val huespedes = mutableListOf<Huesped>()

        reservaSeleccionada?.habitaciones?.forEach { h ->
            if (h.huespedesCheckIn.isNotEmpty()) {
                huespedes.addAll(h.huespedesCheckIn.map { it.huesped })
            }
        }

        if (huespedes.isEmpty() && reservaSeleccionada != null) {
            huespedes.add(reservaSeleccionada!!.huesped)
        }

        adapter = HabitacionCheckInAdapter(habitaciones, huespedes)
        recyclerHabitaciones.layoutManager = LinearLayoutManager(this)
        recyclerHabitaciones.adapter = adapter
    }

    private fun mostrarDatePicker(editText: EditText) {
        val cal = Calendar.getInstance()
        DatePickerDialog(this, { _, y, m, d ->
            editText.setText("%02d/%02d/%04d".format(d, m + 1, y))
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
    }
}
