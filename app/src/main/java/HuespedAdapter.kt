package com.example.tranquidescanso

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HuespedAdapter(
    private val huespedes: List<HuespedItem>,
    private val onEditClick: (HuespedItem) -> Unit
) : RecyclerView.Adapter<HuespedAdapter.HuespedViewHolder>() {

    inner class HuespedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.tvHuespedNombre)
        val documento: TextView = view.findViewById(R.id.tvHuespedDocumento)
        val telefono: TextView = view.findViewById(R.id.tvHuespedTelefono)
        val tipoDocumento: TextView = view.findViewById(R.id.tvHuespedTipoDocumento)
        val btnEditar: Button = view.findViewById(R.id.btnEditarHuesped)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HuespedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_huesped, parent, false)
        return HuespedViewHolder(view)
    }

    override fun onBindViewHolder(holder: HuespedViewHolder, position: Int) {
        val huesped = huespedes[position]
        holder.nombre.text = huesped.nombre
        holder.documento.text = "${huesped.tipoDocumento}: ${huesped.documento}"
        holder.telefono.text = "Tel: ${huesped.telefono}"

        holder.btnEditar.setOnClickListener {
            onEditClick(huesped)
        }


    }

    override fun getItemCount() = huespedes.size
}
