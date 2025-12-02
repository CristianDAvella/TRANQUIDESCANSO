package com.example.tranquidescanso.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Huesped

class HuespedAdapter(
    private val huespedes: List<Huesped>,
    private val onAction: (huesped: Huesped, action: String) -> Unit
) : RecyclerView.Adapter<HuespedAdapter.HuespedViewHolder>() {

    inner class HuespedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreHuesped)
        val tvTipoDoc: TextView = view.findViewById(R.id.tvTipoDocumento)
        val tvNumDoc: TextView = view.findViewById(R.id.tvNumeroDocumento)
        val tvTelefonos: TextView = view.findViewById(R.id.tvTelefono)
        val btnEditar: Button = view.findViewById(R.id.btnEditarHuesped)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HuespedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_huesped, parent, false)
        return HuespedViewHolder(view)
    }

    override fun onBindViewHolder(holder: HuespedViewHolder, position: Int) {
        val huesped = huespedes[position]

        holder.tvNombre.text = huesped.nombre
        holder.tvTipoDoc.text = "Tipo: ${huesped.tipoDocumento}"
        holder.tvNumDoc.text = "Doc: ${huesped.numeroDocumento}"

        holder.tvTelefonos.text =
            if (!huesped.telefonos.isNullOrEmpty())
                huesped.telefonos.joinToString(", ")
            else
                "Sin teléfonos"

        holder.btnEditar.setOnClickListener {
            onAction(huesped, "editar")
        }
    }

    override fun getItemCount(): Int = huespedes.size
}
