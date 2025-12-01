package com.example.tranquidescanso.ui.reservas

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agregar_reserva)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_agregar_reserva)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- HUESPEDES ---
        huespedes.add(
            Huesped(
                id = 1,
                nombre = "Carlos Perez",
                tipoDocumento = "CC",
                numeroDocumento = "123",
                telefonos = mutableListOf("300000000"),
                correo = "carlos@gmail.com"
            )
        )

        huespedes.add(
            Huesped(
                id = 2,
                nombre = "Maria Lopez",
                tipoDocumento = "CC",
                numeroDocumento = "456",
                telefonos = mutableListOf("310000000"),
                correo = "maria@gmail.com"
            )
        )

        huespedes.add(
            Huesped(
                id = 3,
                nombre = "Juan Silva",
                tipoDocumento = "CC",
                numeroDocumento = "789",
                telefonos = mutableListOf("320000000"),
                correo = "juan@gmail.com"
            )
        )


        // --- HABITACIONES ---
        habitaciones.add(
            Habitacion(
                id = 1,
                numero = "101",
                tipo = "Sencilla",
                capacidad = 2,
                hotelId = 1,
                hotelNombre = "Hotel Paraíso",
                estado = "Disponible",
                descripcion = "Habitación sencilla"
            )
        )

        habitaciones.add(
            Habitacion(
                id = 2,
                numero = "102",
                tipo = "Doble",
                capacidad = 3,
                hotelId = 1,
                hotelNombre = "Hotel Paraíso",
                estado = "Ocupada",
                descripcion = "Habitación doble"
            )
        )

        habitaciones.add(
            Habitacion(
                id = 3,
                numero = "201",
                tipo = "Suite",
                capacidad = 4,
                hotelId = 2,
                hotelNombre = "Hotel Central",
                estado = "Disponible",
                descripcion = "Suite amplia"
            )
        )

        habitaciones.add(
            Habitacion(
                id = 4,
                numero = "301",
                tipo = "Familiar",
                capacidad = 5,
                hotelId = 3,
                hotelNombre = "Hotel Playa",
                estado = "Disponible",
                descripcion = "Habitación familiar"
            )
        )




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
        val spinnerHabitacion = findViewById<Spinner>(R.id.spinnerHabitacion)
        val btnCrearReserva = findViewById<Button>(R.id.btnCrearReserva)



        val agenciasOpciones = listOf("Ninguna") + agencias
        spinnerAgencia.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, agenciasOpciones).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinnerEstado.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinnerHotel.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hoteles).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }


        cbAnticipo.buttonTintList = android.content.res.ColorStateList.valueOf(
            resources.getColor(R.color.naranjaoscuro)
        )



        etDocumento.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val doc = s.toString().trim()
                val huesped = huespedes.find { it.numeroDocumento == doc }

                tvHuespedResultado.text = if (huesped != null) {
                    "Huésped encontrado: ${huesped.nombre}"
                } else {
                    "No hay ningún huésped con ese documento"
                }
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })



        fun showDatePicker(editText: EditText) {
            val cal = Calendar.getInstance()
            val dpd = DatePickerDialog(
                this,
                R.style.DatePickerNaranja,
                { _, y, m, d ->
                    editText.setText("%02d/%02d/%04d".format(d, m + 1, y))
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }

        etFechaReserva.setOnClickListener { showDatePicker(etFechaReserva) }
        etFechaInicio.setOnClickListener { showDatePicker(etFechaInicio) }
        etFechaFin.setOnClickListener { showDatePicker(etFechaFin) }



        etVencimiento.setText("19:00")
        etVencimiento.setOnClickListener {
            val tpd = TimePickerDialog(
                this,
                R.style.TimePickerNaranja,
                { _, hour, minute ->
                    etVencimiento.setText("%02d:%02d".format(hour, minute))
                },
                19,
                0,
                true
            )
            tpd.show()
        }


        spinnerHotel.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View?,
                position: Int,
                id: Long
            ) {
                val hotelSeleccionado = hoteles[position]
                val disponibles = habitaciones.filter {
                    it.hotelNombre == hotelSeleccionado && it.estado == "Disponible"
                }

                val spinnerData = if (disponibles.isNotEmpty())
                    disponibles.map { it.numero }
                else
                    listOf("No hay habitaciones disponibles")

                spinnerHabitacion.adapter = ArrayAdapter(
                    this@AgregarReservaActivity,
                    android.R.layout.simple_spinner_item,
                    spinnerData
                ).apply {
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        })


        btnCrearReserva.setOnClickListener {
            val doc = etDocumento.text.toString().trim()
            val huesped = huespedes.find { it.numeroDocumento == doc }

            if (huesped == null) {
                Toast.makeText(this, "Huésped no encontrado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val fechaReserva = etFechaReserva.text.toString().trim()
            val fechaInicio = etFechaInicio.text.toString().trim()
            val fechaFin = etFechaFin.text.toString().trim()
            val vencimientoHora = etVencimiento.text.toString().trim()
            val cantidadPersonas = etCantidadPersonas.text.toString().trim().toIntOrNull() ?: 0
            val anticipo = cbAnticipo.isChecked
            val estado = spinnerEstado.selectedItem.toString()
            val hotel = spinnerHotel.selectedItem.toString()
            val habitacionNum = spinnerHabitacion.selectedItem.toString()
            val agencia = if (spinnerAgencia.selectedItem == "Ninguna") null else spinnerAgencia.selectedItem.toString()

            val habitacion = habitaciones.find {
                it.numero == habitacionNum && it.hotelNombre == hotel
            }

            if (fechaReserva.isEmpty() || fechaInicio.isEmpty() || fechaFin.isEmpty() ||
                vencimientoHora.isEmpty() || cantidadPersonas == 0 || habitacion == null
            ) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val reserva = Reserva(
                huesped = huesped,
                hotel = hotel,
                habitacion = habitacion,
                agencia = agencia,
                fechaReserva = fechaReserva,
                fechaInicio = fechaInicio,
                fechaFin = fechaFin,
                vencimiento = vencimientoHora,
                cantidadPersonas = cantidadPersonas,
                anticipoPagado = anticipo,
                estado = estado
            )

            Toast.makeText(this, "Reserva creada para ${huesped.nombre}", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

