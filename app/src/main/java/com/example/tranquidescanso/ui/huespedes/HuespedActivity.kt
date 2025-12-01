package com.example.tranquidescanso.ui.huespedes

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
import com.example.tranquidescanso.adapters.HuespedAdapter
import com.example.tranquidescanso.model.Huesped

class HuespedActivity : AppCompatActivity() {

    private val huespedes = mutableListOf<Huesped>()
    private lateinit var adapter: HuespedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_huesped)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAgregar = findViewById<Button>(R.id.btnAgregarHuesped)
        val rvHuespedes = findViewById<RecyclerView>(R.id.rvHuespedes)

        // --- Lista de prueba ---
        huespedes.addAll(listOf(
            Huesped(1, "Juan Perez", "CC", "12345678", mutableListOf("3001234567"), "juan@mail.com"),
            Huesped(2, "Maria Lopez", "TI", "87654321", mutableListOf("3145678910", "3109998888"), "maria@mail.com")
        ))

        adapter = HuespedAdapter(huespedes) { huesped, action ->
            if (action == "editar") {
                val intent = Intent(this, EditarHuespedActivity::class.java)
                intent.putExtra("huesped", huesped)
                intent.putExtra("position", huespedes.indexOf(huesped))
                startActivityForResult(intent, 101)
            }
        }

        rvHuespedes.layoutManager = LinearLayoutManager(this)
        rvHuespedes.adapter = adapter

        // Botón agregar
        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarHuespedActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            when(requestCode) {
                100 -> { // Agregar
                    val nuevo = data.getSerializableExtra("huesped") as Huesped
                    huespedes.add(nuevo)
                    adapter.notifyItemInserted(huespedes.size - 1)
                }
                101 -> { // Editar
                    val actualizado = data.getSerializableExtra("huesped_actualizado") as Huesped
                    val pos = data.getIntExtra("position", -1)
                    if (pos != -1) {
                        huespedes[pos] = actualizado
                        adapter.notifyItemChanged(pos)
                    }
                }
            }
        }
    }
}
