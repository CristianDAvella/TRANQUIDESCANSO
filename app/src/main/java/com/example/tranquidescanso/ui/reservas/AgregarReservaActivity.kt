package com.example.tranquidescanso.ui.reservas

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Habitacion
import com.example.tranquidescanso.model.Huesped
import com.example.tranquidescanso.model.Reserva
import java.util.*

class AgregarReservaActivity : AppCompatActivity() {

    private val agencias = listOf("Agencia A", "Agencia B", "Agencia C")
    private val estados = listOf("Pendiente", "Confirmada", "Cancelada", "No usada", "Finalizada")
    private val hoteles = listOf("Hotel Paraíso", "Hotel Central", "Hotel Playa")

    private val huespedes = mutableListOf<Huesped>()
    private val habitaciones = mutableListOf<Habitacion>()

    private val habitacionesSeleccionadas = mutableListOf<Habitacion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_reserva)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_agregar_reserva)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Datos demo
        huespedes.add(Huesped(1, "Carlos Perez", "CC", "123", mutableListOf("300000000"), "carlos@gmail.com"))
        huespedes.add(Huesped(2, "María Lopez", "CC", "456", mutableListOf("310000000"), "maria@gmail.com"))

        habitaciones.add(Habitacion(1, "101", "Sencilla", 2, 1, "Hotel Paraíso", "Disponible", "Vista"))
        habitaciones.add(Habitacion(2, "102", "Doble", 3, 1, "Hotel Paraíso", "Disponible", "Balcón"))
        habitaciones.add(Habitacion(3, "201", "Suite", 4, 2, "Hotel Central", "Disponible", "Suite"))
        habitaciones.add(Habitacion(4, "301", "Familiar", 5, 3, "Hotel Playa", "Disponible", "Familiar"))

        val etDocumento = findViewById<EditText>(R.id.etFiltroDocumento)
        val tvHuespedResultado = findViewById<TextView>(R.id.tvHuespedResultado)
        val spinnerAgencia = findViewById<Spinner>(R.id.spinnerAgencia)
        val etFechaReserva = findViewById<EditText>(R.id.etFechaReserva)
        val etFechaInicio = findViewById<EditText>(R.id.etFechaInicio)
        val etFechaFin = findViewById<EditText>(R.id.etFechaFin)
        val etVencimiento = findViewById<EditText>(R.id.etVencimiento)
        val etCantidadPersonas = findViewById<EditText>(R.id.etCantidadPersonas)
        val cbAnticipo = findViewById<CheckBox>(R.id.cbAnticipo)
        val spinnerEstado = findViewById<Spinner>(R.id.spinnerEstado)
        val spinnerHotel = findViewById<Spinner>(R.id.spinnerHotel)

        val btnAgregarHabitacion = findViewById<Button>(R.id.btnAgregarHabitacionMultiple)
        val llSelectedHabitaciones = findViewById<LinearLayout>(R.id.llSelectedHabitaciones)

        val btnCrearReserva = findViewById<Button>(R.id.btnCrearReserva)

        spinnerAgencia.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("Ninguna") + agencias)
        spinnerEstado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados)
        spinnerHotel.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hoteles)

        // Filtro huésped
        etDocumento.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val huesped = huespedes.find { it.numeroDocumento == s.toString().trim() }
                tvHuespedResultado.text =
                    huesped?.let { "Huésped: ${it.nombre}" } ?: "No encontrado"
            }
        })

        // Calendarios
        fun showDatePicker(editText: EditText) {
            val cal = Calendar.getInstance()
            val dpd = DatePickerDialog(this, R.style.DatePickerNaranja, { _, y, m, d ->
                editText.setText("%02d/%02d/%04d".format(d, m + 1, y))
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))

            dpd.setOnShowListener {
                dpd.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(android.R.color.black))
                dpd.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(android.R.color.black))
            }

            dpd.show()
        }

        etFechaReserva.setOnClickListener { showDatePicker(etFechaReserva) }
        etFechaInicio.setOnClickListener { showDatePicker(etFechaInicio) }
        etFechaFin.setOnClickListener { showDatePicker(etFechaFin) }

        etVencimiento.setOnClickListener {
            val tpd = TimePickerDialog(this, R.style.TimePickerNaranja, { _, h, m ->
                etVencimiento.setText("%02d:%02d".format(h, m))
            }, 19, 0, true)

            tpd.setOnShowListener {
                tpd.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(android.R.color.black))
                tpd.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(android.R.color.black))
            }

            tpd.show()
        }

        // Agregar habitación
        btnAgregarHabitacion.setOnClickListener {
            val hotelSel = spinnerHotel.selectedItem.toString()
            val disponibles = habitaciones.filter { it.hotelNombre == hotelSel && it.estado == "Disponible" }

            if (disponibles.isEmpty()) {
                Toast.makeText(this, "No hay habitaciones disponibles", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val items = disponibles.map { "${it.numero} - ${it.tipo} (${it.capacidad})" }.toTypedArray()

            AlertDialog.Builder(this)
                .setTitle("Seleccionar habitación")
                .setItems(items) { _, pos ->
                    val hab = disponibles[pos]
                    if (!habitacionesSeleccionadas.any { it.id == hab.id }) {
                        habitacionesSeleccionadas.add(hab)

                        val tv = TextView(this)
                        tv.text = "${hab.numero} - ${hab.tipo} (${hab.capacidad})"
                        tv.setPadding(10, 8, 10, 8)
                        llSelectedHabitaciones.addView(tv)
                    }
                }
                .show()
        }

        // Crear reserva
        btnCrearReserva.setOnClickListener {
            val doc = etDocumento.text.toString().trim()
            val huesped = huespedes.find { it.numeroDocumento == doc }
            if (huesped == null) {
                Toast.makeText(this, "Huésped no encontrado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (habitacionesSeleccionadas.isEmpty()) {
                Toast.makeText(this, "Agrega al menos 1 habitación", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nueva = Reserva(
                id = Random().nextInt(999999),
                huesped = huesped,
                hotel = spinnerHotel.selectedItem.toString(),
                habitaciones = habitacionesSeleccionadas.toMutableList(),
                agencia = spinnerAgencia.selectedItem.toString().takeIf { it != "Ninguna" },
                fechaReserva = etFechaReserva.text.toString(),
                fechaInicio = etFechaInicio.text.toString(),
                fechaFin = etFechaFin.text.toString(),
                vencimiento = etVencimiento.text.toString(),
                cantidadPersonas = etCantidadPersonas.text.toString().toIntOrNull() ?: 0,
                anticipoPagado = cbAnticipo.isChecked,
                estado = spinnerEstado.selectedItem.toString()
            )

            Toast.makeText(this, "Reserva creada!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
