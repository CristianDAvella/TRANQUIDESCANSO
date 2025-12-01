package com.example.tranquidescanso.ui.reservas

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.listaGlobalReservas
import com.example.tranquidescanso.model.Reserva
import java.util.*

class EditarReservaActivity : AppCompatActivity() {

    private var reservaId: Int = -1
    private var reservaActual: Reserva? = null

    private lateinit var etNombre: EditText
    private lateinit var etDocumento: EditText
    private lateinit var etHotel: EditText

    private lateinit var etFechaReserva: EditText
    private lateinit var etFechaInicio: EditText
    private lateinit var etFechaFin: EditText
    private lateinit var etVencimiento: EditText

    private lateinit var etCantidadPersonas: EditText
    private lateinit var swAnticipo: Switch
    private lateinit var etEstadoReserva: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_reserva)

        reservaId = intent.getIntExtra("idReserva", -1)
        reservaActual = listaGlobalReservas.find { it.id == reservaId }

        if (reservaActual == null) {
            Toast.makeText(this, "Error cargando reserva", Toast.LENGTH_LONG).show()
            finish()
        }

        inicializarViews()
        cargarDatosEnViews()
        configurarDatePickers()
        configurarBotonGuardar()
    }

    private fun inicializarViews() {
        etNombre = findViewById(R.id.etNombre)
        etDocumento = findViewById(R.id.etDocumento)
        etHotel = findViewById(R.id.etHotel)

        etFechaReserva = findViewById(R.id.etFechaReserva)
        etFechaInicio = findViewById(R.id.etFechaInicio)
        etFechaFin = findViewById(R.id.etFechaFin)
        etVencimiento = findViewById(R.id.etVencimiento)

        etCantidadPersonas = findViewById(R.id.etCantidadPersonas)
        swAnticipo = findViewById(R.id.swAnticipo)
        etEstadoReserva = findViewById(R.id.etEstadoReserva)
        btnGuardar = findViewById(R.id.btnGuardar)
    }

    private fun cargarDatosEnViews() {
        val r = reservaActual ?: return

        etNombre.setText(r.huesped.nombre)
        etDocumento.setText(r.huesped.numeroDocumento)
        etHotel.setText(r.hotel)

        etFechaReserva.setText(r.fechaReserva)
        etFechaInicio.setText(r.fechaInicio)
        etFechaFin.setText(r.fechaFin)
        etVencimiento.setText(r.vencimiento)

        etCantidadPersonas.setText(r.cantidadPersonas.toString())
        swAnticipo.isChecked = r.anticipoPagado
        etEstadoReserva.setText(r.estado)
    }

    private fun configurarDatePickers() {
        val campos = listOf(etFechaReserva, etFechaInicio, etFechaFin, etVencimiento)
        campos.forEach { editText ->
            editText.setOnClickListener {
                val calendar = Calendar.getInstance()
                val dialog = DatePickerDialog(
                    this,
                    { _, y, m, d -> editText.setText("%02d/%02d/%04d".format(d, m + 1, y)) },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                dialog.show()
            }
        }
    }

    private fun configurarBotonGuardar() {
        btnGuardar.setOnClickListener {
            val r = reservaActual ?: return@setOnClickListener

            r.huesped.nombre = etNombre.text.toString()
            r.huesped.numeroDocumento = etDocumento.text.toString()
            r.hotel = etHotel.text.toString()

            r.fechaReserva = etFechaReserva.text.toString()
            r.fechaInicio = etFechaInicio.text.toString()
            r.fechaFin = etFechaFin.text.toString()
            r.vencimiento = etVencimiento.text.toString()

            r.cantidadPersonas = etCantidadPersonas.text.toString().toIntOrNull() ?: r.cantidadPersonas
            r.anticipoPagado = swAnticipo.isChecked
            r.estado = etEstadoReserva.text.toString()

            Toast.makeText(this, "Reserva actualizada", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
