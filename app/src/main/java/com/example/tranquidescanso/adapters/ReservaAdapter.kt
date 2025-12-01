package com.example.tranquidescanso.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Reserva

// LISTA GLOBAL (no crea archivo nuevo; sitúalo aquí)
val listaGlobalReservas: MutableList<Reserva> = mutableListOf()

class ReservaAdapter(
    private val reservas: MutableList<Reserva>,
    private val listener: (reserva: Reserva, action: String) -> Unit
) : RecyclerView.Adapter<ReservaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvHuesped: TextView = view.findViewById(R.id.tvHuesped)
        val tvHotel: TextView = view.findViewById(R.id.tvHotel)
        val tvHabitacion: TextView = view.findViewById(R.id.tvHabitacion)
        val tvEstado: TextView = view.findViewById(R.id.tvEstado)
        val btnEditar: Button = view.findViewById(R.id.btnEditarReserva)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reserva, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reserva = reservas[position]
        holder.tvHuesped.text = "Huésped: ${reserva.huesped.nombre} (${reserva.huesped.numeroDocumento})"
        holder.tvHotel.text = "Hotel: ${reserva.hotel}"
        holder.tvHabitacion.text = "Habitación: ${reserva.habitacion.numero} (${reserva.habitacion.tipo})"
        holder.tvEstado.text = "Estado: ${reserva.estado}"

        holder.btnEditar.setOnClickListener {
            listener(reserva, "editar")
        }
    }

    override fun getItemCount(): Int = reservas.size
}
