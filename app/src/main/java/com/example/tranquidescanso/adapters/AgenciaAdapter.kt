package com.example.tranquidescanso.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Agencia

class AgenciaAdapter(
    private val agencias: List<Agencia>,
    private val listener: (agencia: Agencia, action: String) -> Unit
) : RecyclerView.Adapter<AgenciaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreAgencia)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_agencia, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val agencia = agencias[position]
        holder.tvNombre.text = agencia.nombre


    }

    override fun getItemCount(): Int = agencias.size
}
