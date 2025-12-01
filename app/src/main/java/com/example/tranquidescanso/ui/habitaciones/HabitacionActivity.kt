package com.example.tranquidescanso.ui.habitaciones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.HabitacionAdapter
import com.example.tranquidescanso.model.Habitacion

class HabitacionActivity : AppCompatActivity() {

    private val habitaciones = mutableListOf<Habitacion>()
    private lateinit var habitacionAdapter: HabitacionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_habitacion)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_habitacion)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAgregarHabitacion = findViewById<Button>(R.id.btnAgregarHabitacion)
        val rvHabitaciones = findViewById<RecyclerView>(R.id.rvHabitaciones)

        // --- LISTA DE PRUEBA ---
        habitaciones.addAll(
            listOf(
                Habitacion(1, "101", "Sencilla", 2, 1, false, "Vista al jardín"),
                Habitacion(2, "102", "Doble", 4, 1, false, "Con balcón"),
                Habitacion(3, "201", "Suite", 3, 2, false, "Suite presidencial")
            )
        )

        habitacionAdapter = HabitacionAdapter(habitaciones) { habitacion, action, position ->
            when(action) {
                "editar" -> {
                    val intent = Intent(this, EditarHabitacionActivity::class.java)
                    intent.putExtra("habitacion", habitacion)
                    intent.putExtra("position", position)
                    startActivityForResult(intent, 1001)
                }
            }
        }

        rvHabitaciones.layoutManager = LinearLayoutManager(this)
        rvHabitaciones.adapter = habitacionAdapter

        btnAgregarHabitacion.setOnClickListener {
            val intent = Intent(this, AgregarHabitacionActivity::class.java)
            startActivityForResult(intent, 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && data != null){
            val habitacion = data.getSerializableExtra("habitacion") as Habitacion
            val position = data.getIntExtra("position", -1)

            if(requestCode == 1000){
                // Nueva habitación
                habitaciones.add(habitacion)
            } else if(requestCode == 1001 && position >= 0){
                // Editar habitación existente
                habitaciones[position] = habitacion
            }

            habitacionAdapter.notifyDataSetChanged()
        }
    }
}
