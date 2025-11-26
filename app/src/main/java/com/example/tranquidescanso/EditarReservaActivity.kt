package com.example.tranquidescanso

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class EditarReservaActivity : AppCompatActivity() {

    private lateinit var etHuesped: EditText
    private lateinit var etAgencia: EditText
    private lateinit var etFechaInicio: EditText
    private lateinit var etFechaFin: EditText
    private lateinit var etHabitaciones: EditText
    private lateinit var btnGuardarReserva: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_reserva)

        etHuesped = findViewById(R.id.etNombreHuesped)
        etAgencia = findViewById(R.id.etAgencia)
        etFechaInicio = findViewById(R.id.etFechaInicio)
        etFechaFin = findViewById(R.id.etFechaFin)
        etHabitaciones = findViewById(R.id.etHabitaciones)
        btnGuardarReserva = findViewById(R.id.btnGuardarReserva)

        // Cargar datos existentes
        etHuesped.setText(intent.getStringExtra("huesped") ?: "")
        etAgencia.setText(intent.getStringExtra("agencia") ?: "")
        etFechaInicio.setText(intent.getStringExtra("fechaInicio") ?: "")
        etFechaFin.setText(intent.getStringExtra("fechaFin") ?: "")
        etHabitaciones.setText(intent.getIntExtra("habitaciones", 1).toString())

        etFechaInicio.setOnClickListener { mostrarDatePicker(etFechaInicio) }
        etFechaFin.setOnClickListener { mostrarDatePicker(etFechaFin) }

        btnGuardarReserva.setOnClickListener {
            val huesped = etHuesped.text.toString()
            val agencia = etAgencia.text.toString()
            val fechaInicio = etFechaInicio.text.toString()
            val fechaFin = etFechaFin.text.toString()
            val habitaciones = etHabitaciones.text.toString().toIntOrNull() ?: 1
            val id = intent.getIntExtra("id", -1)

            if (huesped.isEmpty() || fechaInicio.isEmpty() || fechaFin.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val resultado = Intent()
                resultado.putExtra("id", id)
                resultado.putExtra("huesped", huesped)
                resultado.putExtra("agencia", agencia)
                resultado.putExtra("fechaInicio", fechaInicio)
                resultado.putExtra("fechaFin", fechaFin)
                resultado.putExtra("habitaciones", habitaciones)
                setResult(RESULT_OK, resultado)
                finish()
            }
        }
    }

    private fun mostrarDatePicker(editText: EditText) {
        val cal = Calendar.getInstance()
        val dialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                editText.setText("%04d-%02d-%02d".format(year, month + 1, day))
            },
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()
    }
}
