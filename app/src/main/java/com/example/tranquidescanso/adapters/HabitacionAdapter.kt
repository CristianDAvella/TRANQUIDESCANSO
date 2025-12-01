package com.example.tranquidescanso.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Habitacion  // <--- IMPORT CORRECTO

class HabitacionAdapter(private val lista: List<Habitacion>) :
    RecyclerView.Adapter<HabitacionAdapter.HabitacionViewHolder>() {

    class HabitacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNumero: TextView = itemView.findViewById(R.id.tvNumero)
        val tvTipo: TextView = itemView.findViewById(R.id.tvTipo)
        val tvCapacidad: TextView = itemView.findViewById(R.id.tvCapacidad)
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstado)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val btnEditar: Button = itemView.findViewById(R.id.btnEditarHabitacion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitacionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habitacion, parent, false)
        return HabitacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitacionViewHolder, position: Int) {
        val habitacion = lista[position]

        // Ajustamos los campos a los que tiene tu modelo
        holder.tvNumero.text = "Número: ${habitacion.numero}"
        holder.tvTipo.text = "Tipo: ${habitacion.tipo}"
        holder.tvCapacidad.text = "Capacidad: ${habitacion.capacidad}"
        holder.tvEstado.text = "Estado: ${habitacion.estado}"
        holder.tvDescripcion.text = "Hotel: ${habitacion.hotelNombre}"  // antes era hotel

        holder.btnEditar.setOnClickListener {
            // Aquí puedes abrir EditarHabitacionActivity si quieres
        }
    }

    override fun getItemCount(): Int = lista.size
}
