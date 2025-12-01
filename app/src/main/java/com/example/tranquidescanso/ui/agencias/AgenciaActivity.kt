package com.example.tranquidescanso.ui.agencias

import android.app.Activity
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
import com.example.tranquidescanso.adapters.AgenciaAdapter
import com.example.tranquidescanso.model.Agencia

class AgenciaActivity : AppCompatActivity() {

    private val agencias = mutableListOf<Agencia>()
    private lateinit var agenciaAdapter: AgenciaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_agencia)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_agencia)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAgregarAgencia = findViewById<Button>(R.id.btnAgregarAgencia)
        val rvAgencias = findViewById<RecyclerView>(R.id.rvAgencias)

        agenciaAdapter = AgenciaAdapter(agencias) { _, _ ->
            // Por ahora no hacemos nada al editar
        }

        rvAgencias.layoutManager = LinearLayoutManager(this)
        rvAgencias.adapter = agenciaAdapter

        btnAgregarAgencia.setOnClickListener {
            val intent = Intent(this, AgregarAgenciaActivity::class.java)
            startActivityForResult(intent, 2000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && data != null && requestCode == 2000){
            val agencia = data.getSerializableExtra("agencia") as Agencia
            agencias.add(agencia)
            agenciaAdapter.notifyDataSetChanged()
        }
    }
}
