package com.example.tranquidescanso.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Hotel

class HotelAdapter(
    private val hoteles: List<Hotel>,
    private val onActionClick: (Hotel, String) -> Unit
) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    inner class HotelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreHotel)
        val tvDireccion: TextView = view.findViewById(R.id.tvDireccionHotel)
        val tvCategoria: TextView = view.findViewById(R.id.tvCategoriaHotel)
        val btnEditar: Button = view.findViewById(R.id.btnEditar)
        val btnHistorial: Button = view.findViewById(R.id.btnHistorial)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hoteles[position]
        holder.tvNombre.text = hotel.nombre
        holder.tvDireccion.text = hotel.direccion
        holder.tvCategoria.text = hotel.categoria

        holder.btnEditar.setOnClickListener { onActionClick(hotel, "editar") }
        holder.btnHistorial.setOnClickListener { onActionClick(hotel, "historial") }
    }

    override fun getItemCount(): Int = hoteles.size
}
