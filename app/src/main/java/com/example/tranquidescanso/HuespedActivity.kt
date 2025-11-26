package com.example.tranquidescanso

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class HuespedActivity : AppCompatActivity() {

    private val huespedes = mutableListOf<HuespedItem>()
    private lateinit var adapter: HuespedAdapter

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            data?.let {
                val id = it.getIntExtra("huespedId", -1).let { if (it == -1) huespedes.size else it }
                val huesped = HuespedItem(
                    id,
                    it.getStringExtra("huespedNombre") ?: "",
                    it.getStringExtra("huespedDocumento") ?: "",
                    it.getStringExtra("huespedTelefono") ?: "",
                    it.getStringExtra("huespedTipoDocumento") ?: ""
                )
                if (id < huespedes.size) {

                    huespedes[id] = huesped
                } else {

                    huespedes.add(huesped)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huesped)

        val recyclerView: RecyclerView = findViewById(R.id.rvHuespedes)
        adapter = HuespedAdapter(huespedes) { huesped ->

            val intent = Intent(this, HuespedDetalleActivity::class.java).apply {
                putExtra("huespedId", huesped.id)
                putExtra("huespedNombre", huesped.nombre)
                putExtra("huespedDocumento", huesped.documento)
                putExtra("huespedTelefono", huesped.telefono)
                putExtra("huespedTipoDocumento", huesped.tipoDocumento)
            }
            launcher.launch(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        val btnAgregar: MaterialButton = findViewById(R.id.btnAgregarHuesped)
        btnAgregar.setOnClickListener {
            val intent = Intent(this, HuespedDetalleActivity::class.java)
            launcher.launch(intent)
        }
    }
}
