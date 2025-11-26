package com.example.tranquidescanso

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservaAdapter(
    private val lista: List<ReservaItem>,
    private val onDetalleClick: (ReservaItem) -> Unit
) : RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {

    inner class ReservaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvHuesped: TextView = view.findViewById(R.id.tvHuesped)
        val tvAgencia: TextView = view.findViewById(R.id.tvAgencia)
        val tvFechas: TextView = view.findViewById(R.id.tvFechas)
        val tvHabitaciones: TextView = view.findViewById(R.id.tvHabitaciones)
        val btnVerDetalle: Button = view.findViewById(R.id.btnVerDetalle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reserva, parent, false)
        return ReservaViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val reserva = lista[position]
        holder.tvHuesped.text = "Huésped: ${reserva.huesped}"
        holder.tvAgencia.text = "Agencia: ${reserva.agencia ?: "Ninguna"}"
        holder.tvFechas.text = "Del ${reserva.fechaInicio} al ${reserva.fechaFin}"
        holder.tvHabitaciones.text = "Habitaciones: ${reserva.habitaciones}"

        holder.btnVerDetalle.setOnClickListener { onDetalleClick(reserva) }
    }
}
