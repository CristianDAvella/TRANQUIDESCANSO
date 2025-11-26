package com.example.tranquidescanso

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class HotelAdapter(
    private val hoteles: List<HotelItem>
) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    inner class HotelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.tvHotelNombre)
        val direccion: TextView = view.findViewById(R.id.tvHotelDireccion)
        val categoria: TextView = view.findViewById(R.id.tvHotelCategoria)
        val btnEditar: Button = view.findViewById(R.id.btnEditarHotel)
        val btnHistorial: Button = view.findViewById(R.id.btnHistorialHotel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hoteles[position]

        // Asignar valores al item
        holder.nombre.text = hotel.nombre
        holder.direccion.text = hotel.direccion
        holder.categoria.text = hotel.categoria

        // --- BOTÓN EDITAR ---
        holder.btnEditar.setOnClickListener { view ->
            val ctx = view.context

            val intent = Intent(ctx, HotelDetalleActivity::class.java).apply {
                putExtra("hotelId", hotel.id)
                putExtra("hotelNombre", hotel.nombre)
                putExtra("hotelDireccion", hotel.direccion)
                putExtra("hotelTelefono", hotel.telefono)
                putExtra("hotelAno", hotel.ano)
                putExtra("hotelCategoria", hotel.categoria)
            }

            ctx.startActivity(intent)
        }

        // --- BOTÓN HISTORIAL ---
        holder.btnHistorial.setOnClickListener { view ->
            Toast.makeText(
                view.context,
                "Historial de ${hotel.nombre}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = hoteles.size
}
