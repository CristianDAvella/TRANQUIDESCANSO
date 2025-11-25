package com.example.tranquidescanso

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
        holder.nombre.text = hotel.nombre
        holder.direccion.text = hotel.direccion
        holder.categoria.text = hotel.categoria

        // Botón Editar → abrir HotelDetalleActivity
        holder.btnEditar.setOnClickListener {
            val intent = Intent(it.context, HotelDetalleActivity::class.java)
            intent.putExtra("hotelId", hotel.id)
            intent.putExtra("hotelNombre", hotel.nombre)
            intent.putExtra("hotelDireccion", hotel.direccion)
            intent.putExtra("hotelCategoria", hotel.categoria)
            it.context.startActivity(intent)
        }



        // Botón Historial → abrir otra Activity o mostrar Toast
        holder.btnHistorial.setOnClickListener {
            // Por ahora, Toast
            android.widget.Toast.makeText(it.context, "Historial ${hotel.nombre}", android.widget.Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = hoteles.size
}
