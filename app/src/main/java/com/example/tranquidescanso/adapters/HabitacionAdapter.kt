package com.example.tranquidescanso.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Habitacion

class HabitacionAdapter(
    private val habitaciones: List<Habitacion>,
    private val listener: (habitacion: Habitacion, action: String, position: Int) -> Unit
) : RecyclerView.Adapter<HabitacionAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNumero: TextView = view.findViewById(R.id.tvNumero)
        val tvTipo: TextView = view.findViewById(R.id.tvTipo)
        val tvCapacidad: TextView = view.findViewById(R.id.tvCapacidad)
        val tvDescripcion: TextView = view.findViewById(R.id.tvDescripcion)
        val btnEditar: Button = view.findViewById(R.id.btnEditarHabitacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habitacion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habitacion = habitaciones[position]
        holder.tvNumero.text = "Número: ${habitacion.numero}"
        holder.tvTipo.text = "Tipo: ${habitacion.tipo}"
        holder.tvCapacidad.text = "Capacidad: ${habitacion.capacidad}"
        holder.tvDescripcion.text = "Descripción: ${habitacion.descripcion}"

        holder.btnEditar.setOnClickListener {
            listener(habitacion, "editar", position)
        }
    }

    override fun getItemCount(): Int = habitaciones.size
}
