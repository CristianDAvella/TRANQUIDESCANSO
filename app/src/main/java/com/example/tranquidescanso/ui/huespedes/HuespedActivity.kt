package com.example.tranquidescanso.ui.huesped

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.adapters.HuespedAdapter
import com.example.tranquidescanso.model.Huesped
import com.example.tranquidescanso.repository.HuespedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.tranquidescanso.ui.huespedes.AgregarHuespedActivity
import com.example.tranquidescanso.ui.huespedes.EditarHuespedActivity

class HuespedActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HuespedAdapter
    private val repository = HuespedRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huesped)

        recyclerView = findViewById(R.id.rvHuespedes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val btnAgregar = findViewById<Button>(R.id.btnAgregarHuesped)

        btnAgregar.setOnClickListener {
            startActivity(Intent(this, AgregarHuespedActivity::class.java))
        }

        cargarHuespedes()
    }

    private fun cargarHuespedes() {
        CoroutineScope(Dispatchers.IO).launch {
            val lista = repository.obtenerHuespedes()

            withContext(Dispatchers.Main) {
                if (lista != null) {

                    adapter = HuespedAdapter(lista) { huesped, action ->
                        if (action == "editar") {
                            val intent = Intent(this@HuespedActivity, EditarHuespedActivity::class.java)
                            intent.putExtra("huesped", huesped)
                            startActivity(intent)
                        }
                    }

                    recyclerView.adapter = adapter

                } else {
                    Toast.makeText(
                        this@HuespedActivity,
                        "Error cargando huéspedes",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
