package com.example.tranquidescanso

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.tranquidescanso.model.Agencia
import com.example.tranquidescanso.model.TipoHabitacion
import kotlin.random.Random
import java.text.SimpleDateFormat
import java.util.*

class CrearReservaActivity : AppCompatActivity() {

    private lateinit var etBuscarHuesped: EditText
    private lateinit var btnCrearHuesped: Button
    private lateinit var tvHuespedSeleccionado: TextView
    private lateinit var spinnerAgencia: Spinner
    private lateinit var btnCrearAgencia: Button
    private lateinit var etFechaInicio: EditText
    private lateinit var etFechaFin: EditText
    private lateinit var etCantidadPersonas: EditText
    private lateinit var etCantidadHabitaciones: EditText
    private lateinit var containerTiposHabitacion: LinearLayout
    private lateinit var btnCrearReserva: Button

    private var idHuespedSeleccionado: Int? = null
    private val agenciasList = mutableListOf<Agencia>()
    private val tiposHabitacionList = mutableListOf<TipoHabitacion>()
    private val cantidadPorTipo = mutableMapOf<Int, Int>()

    private val REQUEST_CREAR_HUESPED = 101
    private val REQUEST_CREAR_AGENCIA = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_reserva)

        bindViews()
        cargarDatosSimulados()
        setupAgenciaSpinner()
        setupFechaPickers()
        cargarTiposHabitacion()
        setupBuscarHuesped()

        btnCrearReserva.setOnClickListener { validarYGuardarReserva() }

        btnCrearHuesped.setOnClickListener {
            val intent = Intent(this, HuespedDetalleActivity::class.java)
            startActivityForResult(intent, REQUEST_CREAR_HUESPED)
        }

        btnCrearAgencia.setOnClickListener {
            val intent = Intent(this, CrearAgenciaActivity::class.java)
            startActivityForResult(intent, REQUEST_CREAR_AGENCIA)
        }
    }

    private fun bindViews() {
        etBuscarHuesped = findViewById(R.id.etBuscarHuesped)
        btnCrearHuesped = findViewById(R.id.btnCrearHuesped)
        tvHuespedSeleccionado = findViewById(R.id.tvHuespedSeleccionado)
        spinnerAgencia = findViewById(R.id.spinnerAgencia)
        btnCrearAgencia = findViewById(R.id.btnCrearAgencia)
        etFechaInicio = findViewById(R.id.etFechaInicio)
        etFechaFin = findViewById(R.id.etFechaFin)
        etCantidadPersonas = findViewById(R.id.etCantidadPersonas)
        etCantidadHabitaciones = findViewById(R.id.etCantidadHabitaciones)
        containerTiposHabitacion = findViewById(R.id.containerTiposHabitacion)
        btnCrearReserva = findViewById(R.id.btnCrearReserva)
    }

    private fun cargarDatosSimulados() {
        // Agencias simuladas
        agenciasList.add(Agencia(1, "Agencia Alpha", "3001112233"))
        agenciasList.add(Agencia(2, "Agencia Beta", "3002223344"))

        // Tipos de habitación simulados
        tiposHabitacionList.add(TipoHabitacion(1, "Sencilla"))
        tiposHabitacionList.add(TipoHabitacion(2, "Doble"))
        tiposHabitacionList.add(TipoHabitacion(3, "Suite"))
    }

    private fun setupAgenciaSpinner() {
        actualizarSpinnerAgencias()
    }

    private fun actualizarSpinnerAgencias() {
        val nombresAgencias = mutableListOf("Ninguna")
        nombresAgencias.addAll(agenciasList.map { it.nombre })
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresAgencias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAgencia.adapter = adapter
    }

    private fun obtenerAgenciaSeleccionada(): Agencia? {
        val posicion = spinnerAgencia.selectedItemPosition
        return if (posicion == 0) null else agenciasList[posicion - 1]
    }

    private fun setupFechaPickers() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val c = Calendar.getInstance()

        etFechaInicio.setOnClickListener {
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val fecha = Calendar.getInstance()
                fecha.set(year, month, dayOfMonth)
                etFechaInicio.setText(dateFormat.format(fecha.time))
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }

        etFechaFin.setOnClickListener {
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val fecha = Calendar.getInstance()
                fecha.set(year, month, dayOfMonth)
                etFechaFin.setText(dateFormat.format(fecha.time))
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun cargarTiposHabitacion() {
        tiposHabitacionList.forEach { tipo ->
            val view = layoutInflater.inflate(R.layout.item_tipo_habitacion, containerTiposHabitacion, false)
            val tvNombre = view.findViewById<TextView>(R.id.tvTipoHabitacion)
            val etCantidad = view.findViewById<EditText>(R.id.etCantidadTipo)

            tvNombre.text = tipo.nombre
            etCantidad.setText("0")
            etCantidad.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    cantidadPorTipo[tipo.id] = s.toString().toIntOrNull() ?: 0
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            containerTiposHabitacion.addView(view)
        }
    }

    private fun setupBuscarHuesped() {
        etBuscarHuesped.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                if (text.length >= 3) {
                    val huesped = buscarHuespedPorIdentificacion(text)
                    if (huesped != null) {
                        idHuespedSeleccionado = huesped.id
                        tvHuespedSeleccionado.text = "${huesped.nombre} - ${huesped.documento}"
                    } else {
                        tvHuespedSeleccionado.text = "No existe huésped con esta identificación"
                        idHuespedSeleccionado = null
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun buscarHuespedPorIdentificacion(identificacion: String): HuespedItem? {
        val listaSimulada = listOf(
            HuespedItem(1, "Juan Perez", "123456", "3001112233", "juan@mail.com", "Cédula"),
            HuespedItem(2, "Maria Lopez", "987654", "3002223344", "maria@mail.com", "Cédula")
        )
        return listaSimulada.find { it.documento.startsWith(identificacion) }
    }

    private fun validarYGuardarReserva() {
        if (idHuespedSeleccionado == null) {
            Toast.makeText(this, "Debe seleccionar un huésped", Toast.LENGTH_SHORT).show()
            return
        }

        val inicio = etFechaInicio.text.toString()
        val fin = etFechaFin.text.toString()
        val cantPersonas = etCantidadPersonas.text.toString().toIntOrNull() ?: 0
        val cantHabitaciones = etCantidadHabitaciones.text.toString().toIntOrNull() ?: 0

        if (inicio >= fin) {
            Toast.makeText(this, "Fecha fin debe ser mayor a inicio", Toast.LENGTH_SHORT).show()
            return
        }

        val sumaTipos = cantidadPorTipo.values.sum()
        if (sumaTipos != cantHabitaciones) {
            Toast.makeText(this, "La suma de tipos debe igualar la cantidad de habitaciones", Toast.LENGTH_SHORT).show()
            return
        }

        // Simulación guardado de reserva
        val reservaId = Random.nextInt(1000)
        cantidadPorTipo.forEach { (tipoId, cantidad) ->
            if (cantidad > 0)
                println("DETALLE_RESERVA -> reservaId:$reservaId, tipo:$tipoId, cantidad:$cantidad")
        }

        Toast.makeText(this, "Reserva creada con éxito", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Resultado de Huesped
        if (requestCode == REQUEST_CREAR_HUESPED && resultCode == RESULT_OK) {
            data?.let {
                idHuespedSeleccionado = it.getIntExtra("huespedId", -1)
                tvHuespedSeleccionado.text =
                    "${it.getStringExtra("huespedNombre")} - ${it.getStringExtra("huespedDocumento")}"
            }
        }

        // Resultado de Agencia
        if (requestCode == REQUEST_CREAR_AGENCIA && resultCode == RESULT_OK) {
            data?.let {
                val nuevaAgencia = Agencia(
                    it.getIntExtra("agenciaId", 0),
                    it.getStringExtra("agenciaNombre") ?: "",
                    it.getStringExtra("agenciaTelefono") ?: ""
                )
                agenciasList.add(nuevaAgencia)
                actualizarSpinnerAgencias()
                spinnerAgencia.setSelection(agenciasList.size) // Selecciona la recién creada
            }
        }
    }
}
