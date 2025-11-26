package com.example.tranquidescanso

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReservaAdapter(
    private val lista: List<ReservaItem>,
    private val onDetalleClick: (ReservaItem) -> Unit,
    private val onEditarClick: (ReservaItem) -> Unit,
    private val onCancelarClick: (ReservaItem) -> Unit
) : RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {

    inner class ReservaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombreHuesped: TextView = view.findViewById(R.id.tvNombreHuesped)
        val tvFechaReserva: TextView = view.findViewById(R.id.tvFechaReserva)
        val tvNumeroPersonas: TextView = view.findViewById(R.id.tvNumeroPersonas)
        val btnDetallesReserva: Button = view.findViewById(R.id.btnDetallesReserva)
        val btnEditarReserva: Button = view.findViewById(R.id.btnEditarReserva)
        val btnCancelarReserva: Button = view.findViewById(R.id.btnCancelarReserva)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reserva, parent, false)
        return ReservaViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val reserva = lista[position]
        holder.tvNombreHuesped.text = "Huésped: ${reserva.huesped}"
        holder.tvFechaReserva.text = "Del ${reserva.fechaInicio} al ${reserva.fechaFin}"
        holder.tvNumeroPersonas.text = "Habitaciones: ${reserva.habitaciones}"

        holder.btnDetallesReserva.setOnClickListener { onDetalleClick(reserva) }
        holder.btnEditarReserva.setOnClickListener { onEditarClick(reserva) }
        holder.btnCancelarReserva.setOnClickListener { onCancelarClick(reserva) }
    }
}
