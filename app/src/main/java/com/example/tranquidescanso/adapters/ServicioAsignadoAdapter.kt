package com.example.tranquidescanso.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Servicio

class ServicioAsignadoAdapter(
    private val servicios: List<Servicio>
) : RecyclerView.Adapter<ServicioAsignadoAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreServicio)
        val tvCosto: TextView = view.findViewById(R.id.tvCostoServicio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_servicio_asignado, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = servicios.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val servicio = servicios[position]
        holder.tvNombre.text = servicio.nombre
        holder.tvCosto.text = "${servicio.costo}"
    }
}
