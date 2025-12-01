package com.example.tranquidescanso.ui.reservas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.listaGlobalReservas
import com.example.tranquidescanso.model.Habitacion
import com.example.tranquidescanso.model.Huesped
import com.example.tranquidescanso.model.Reserva
import java.util.*

class EditarReservaActivity : AppCompatActivity() {

    private var idReserva: Int = -1
    private var reservaSeleccionada: Reserva? = null

    private lateinit var tvIdReserva: TextView
    private lateinit var etNombreHuesped: EditText
    private lateinit var etTipoDoc: EditText
    private lateinit var etNumDoc: EditText
    private lateinit var etTelefonos: EditText
    private lateinit var etCorreo: EditText

    private lateinit var etHotel: EditText
    private lateinit var etHabitacion: EditText
    private lateinit var etTipoHabitacion: EditText
    private lateinit var etCapacidad: EditText
    private lateinit var etDescripcionHabitacion: EditText
    private lateinit var etEstadoHabitacion: EditText

    private lateinit var etFechaReserva: EditText
    private lateinit var etFechaInicio: EditText
    private lateinit var etFechaFin: EditText
    private lateinit var etVencimiento: EditText
    private lateinit var etCantidadPersonas: EditText
    private lateinit var cbAnticipo: CheckBox
    private lateinit var spEstadoReserva: Spinner
    private lateinit var btnGuardarCambios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_reserva)

        idReserva = intent.getIntExtra("idReserva", -1)
        reservaSeleccionada = listaGlobalReservas.find { it.id == idReserva }

        if (reservaSeleccionada == null) {
            Toast.makeText(this, "Reserva no encontrada", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        bindViews()
        cargarDatosEnViews()
        configurarDateTimePickers()
        configurarBotonGuardar()
    }

    private fun bindViews() {
        tvIdReserva = findViewById(R.id.tvIdReserva)

        etNombreHuesped = findViewById(R.id.etHuespedEditar)
        etTipoDoc = findViewById(R.id.etTipoDocEditar)
        etNumDoc = findViewById(R.id.etNumDocEditar)
        etTelefonos = findViewById(R.id.etTelefonosEditar)
        etCorreo = findViewById(R.id.etCorreoEditar)

        etHotel = findViewById(R.id.etHotelEditar)
        etHabitacion = findViewById(R.id.etHabitacionEditar)
        etTipoHabitacion = findViewById(R.id.etTipoHabitacionEditar)
        etCapacidad = findViewById(R.id.etCapacidadHabitacionEditar)
        etDescripcionHabitacion = findViewById(R.id.etDescripcionHabitacionEditar)
        etEstadoHabitacion = findViewById(R.id.etEstadoHabitacionEditar)

        etFechaReserva = findViewById(R.id.etFechaReservaEditar)
        etFechaInicio = findViewById(R.id.etFechaInicioEditar)
        etFechaFin = findViewById(R.id.etFechaFinEditar)
        etVencimiento = findViewById(R.id.etVencimientoEditar)
        etCantidadPersonas = findViewById(R.id.etCantidadPersonasEditar)
        cbAnticipo = findViewById(R.id.cbAnticipoEditar)
        spEstadoReserva = findViewById(R.id.spEstadoEditar)
        btnGuardarCambios = findViewById(R.id.btnGuardarCambios)

        val estados = listOf("Pendiente", "Confirmada", "Cancelada", "No usada", "Finalizada")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spEstadoReserva.adapter = adapter
    }

    private fun cargarDatosEnViews() {
        reservaSeleccionada?.let { r ->

            tvIdReserva.text = "ID Reserva: ${r.id}"

            etNombreHuesped.setText(r.huesped.nombre)
            etTipoDoc.setText(r.huesped.tipoDocumento)
            etNumDoc.setText(r.huesped.numeroDocumento)
            etTelefonos.setText(r.huesped.telefonos.joinToString(","))
            etCorreo.setText(r.huesped.correo)

            etHotel.setText(r.hotel)
            etHabitacion.setText(r.habitacion.numero)
            etTipoHabitacion.setText(r.habitacion.tipo)
            etCapacidad.setText(r.habitacion.capacidad.toString())
            etDescripcionHabitacion.setText(r.habitacion.descripcion)
            etEstadoHabitacion.setText(r.habitacion.estado)

            etFechaReserva.setText(r.fechaReserva)
            etFechaInicio.setText(r.fechaInicio)
            etFechaFin.setText(r.fechaFin)
            etVencimiento.setText(r.vencimiento)
            etCantidadPersonas.setText(r.cantidadPersonas.toString())
            cbAnticipo.isChecked = r.anticipoPagado

            val index = when (r.estado) {
                "Pendiente" -> 0
                "Confirmada" -> 1
                "Cancelada" -> 2
                "No usada" -> 3
                "Finalizada" -> 4
                else -> 0
            }
            spEstadoReserva.setSelection(index)
        }
    }

    private fun configurarDateTimePickers() {

        fun showDatePicker(target: EditText) {
            val cal = Calendar.getInstance()

            val dp = DatePickerDialog(
                this,
                R.style.DatePickerNaranja,   // ← APLICAMOS TU ESTILO
                { _, y, m, d ->
                    target.setText("%02d/%02d/%04d".format(d, m + 1, y))
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            dp.show()
        }

        fun showTimePicker(target: EditText) {
            val cal = Calendar.getInstance()

            val tp = TimePickerDialog(
                this,
                R.style.TimePickerNaranja,   // ← APLICAMOS TU ESTILO
                { _, h, min ->
                    target.setText(String.format("%02d:%02d", h, min))
                },
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            )
            tp.show()
        }

        etFechaReserva.setOnClickListener { showDatePicker(it as EditText) }
        etFechaInicio.setOnClickListener { showDatePicker(it as EditText) }
        etFechaFin.setOnClickListener { showDatePicker(it as EditText) }
        etVencimiento.setOnClickListener { showTimePicker(it as EditText) }
    }

    private fun configurarBotonGuardar() {

        btnGuardarCambios.setOnClickListener {

            if (etNombreHuesped.text.isBlank()) { etNombreHuesped.error = "Nombre requerido"; return@setOnClickListener }
            if (etNumDoc.text.isBlank()) { etNumDoc.error = "Documento requerido"; return@setOnClickListener }
            if (etHotel.text.isBlank()) { etHotel.error = "Hotel requerido"; return@setOnClickListener }
            if (etHabitacion.text.isBlank()) { etHabitacion.error = "Habitación requerida"; return@setOnClickListener }
            if (etFechaInicio.text.isBlank()) { etFechaInicio.error = "Fecha inicio requerida"; return@setOnClickListener }
            if (etFechaFin.text.isBlank()) { etFechaFin.error = "Fecha fin requerida"; return@setOnClickListener }

            val r = reservaSeleccionada ?: return@setOnClickListener

            r.huesped.nombre = etNombreHuesped.text.toString()
            r.huesped.tipoDocumento = etTipoDoc.text.toString()
            r.huesped.numeroDocumento = etNumDoc.text.toString()

            val telfs = etTelefonos.text.toString().split(",").map { it.trim() }.filter { it.isNotEmpty() }
            r.huesped.telefonos = telfs.toMutableList()

            r.huesped.correo = etCorreo.text.toString()

            r.hotel = etHotel.text.toString()
            r.habitacion.numero = etHabitacion.text.toString()
            r.habitacion.tipo = etTipoHabitacion.text.toString()
            r.habitacion.capacidad = etCapacidad.text.toString().toIntOrNull() ?: r.habitacion.capacidad
            r.habitacion.descripcion = etDescripcionHabitacion.text.toString()
            r.habitacion.estado = etEstadoHabitacion.text.toString()

            r.fechaReserva = etFechaReserva.text.toString()
            r.fechaInicio = etFechaInicio.text.toString()
            r.fechaFin = etFechaFin.text.toString()
            r.vencimiento = etVencimiento.text.toString()

            r.cantidadPersonas = etCantidadPersonas.text.toString().toIntOrNull() ?: r.cantidadPersonas
            r.anticipoPagado = cbAnticipo.isChecked
            r.estado = spEstadoReserva.selectedItem.toString()

            Toast.makeText(this, "Reserva actualizada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
