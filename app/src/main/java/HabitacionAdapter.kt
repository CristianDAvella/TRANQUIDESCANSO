package com.example.tranquidescanso.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.HabitacionItem

class HabitacionAdapter(
    private val lista: List<HabitacionItem>,
    private val onItemClick: (HabitacionItem) -> Unit,
    private val onEditarClick: (HabitacionItem) -> Unit,
    private val onReservarClick: (HabitacionItem) -> Unit
) : RecyclerView.Adapter<HabitacionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTipo: TextView = itemView.findViewById(R.id.tvTipoHabitacion)
        val tvNumero: TextView = itemView.findViewById(R.id.tvNumeroHabitacion)
        val tvDisponibilidad: TextView = itemView.findViewById(R.id.tvDisponibilidadHabitacion)

        val btnReservar: Button = itemView.findViewById(R.id.btnReservarHabitacion)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditarHabitacion)

        init {
            itemView.setOnClickListener {
                onItemClick(lista[bindingAdapterPosition])
            }
            btnEditar.setOnClickListener {
                onEditarClick(lista[bindingAdapterPosition])
            }
            btnReservar.setOnClickListener {
                onReservarClick(lista[bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habitacion, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val h = lista[position]

        // Mostrar tipo
        holder.tvTipo.text = h.tipo

        // Mostrar nombre como "Número" de habitación
        holder.tvNumero.text = "Habitación: ${h.nombre}"

        // Mostrar disponibilidad correctamente
        holder.tvDisponibilidad.text =
            if (h.disponibilidad.isEmpty()) "Disponible"
            else h.disponibilidad.joinToString("\n")
    }
}
