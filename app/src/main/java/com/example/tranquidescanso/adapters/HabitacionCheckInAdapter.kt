package com.example.tranquidescanso.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.tranquidescanso.R
import com.example.tranquidescanso.model.Habitacion
import com.example.tranquidescanso.model.Huesped
import com.example.tranquidescanso.model.HuespedCheckIn

class HabitacionCheckInAdapter(
    private val habitaciones: List<Habitacion>,
    private val huespedesReserva: List<Huesped>
) : RecyclerView.Adapter<HabitacionCheckInAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNumero: TextView = view.findViewById(R.id.tvNumeroHabitacion)
        val tvTipo: TextView = view.findViewById(R.id.tvTipoHabitacion)
        val etBuscarHuesped: EditText = view.findViewById(R.id.etBuscarHuesped)
        val llHuespedesAsignados: LinearLayout = view.findViewById(R.id.llHuespedesAsignados)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habitacion_checkin, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = habitaciones.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habitacion = habitaciones[position]

        holder.tvNumero.text = "Habitación: ${habitacion.numero}"
        holder.tvTipo.text = "Tipo: ${habitacion.tipo}"

        // inicializar lista de huéspedes asignados
        if (habitacion.huespedesCheckIn.isEmpty()) {
            habitacion.huespedesCheckIn.addAll(
                huespedesReserva.map { HuespedCheckIn(it) }.take(habitacion.capacidad)
            )
        }

        // mostrar huéspedes asignados dinámicamente
        holder.llHuespedesAsignados.removeAllViews()
        habitacion.huespedesCheckIn.forEachIndexed { index, hc ->
            val view = LayoutInflater.from(holder.itemView.context)
                .inflate(R.layout.item_huesped_checkin, holder.llHuespedesAsignados, false)

            val tvNombre = view.findViewById<TextView>(R.id.tvNombreHuesped)
            val cbResponsable = view.findViewById<CheckBox>(R.id.cbResponsable)
            val cbMascota = view.findViewById<CheckBox>(R.id.cbMascota)

            tvNombre.text = hc.huesped.nombre
            cbResponsable.isChecked = hc.esResponsable
            cbMascota.isChecked = hc.traeMascota

            // Checkbox responsable: solo uno por reserva
            cbResponsable.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // desmarcar otros responsables en todas las habitaciones
                    habitaciones.forEach { h ->
                        h.huespedesCheckIn.forEach { it.esResponsable = false }
                    }
                    hc.esResponsable = true
                    notifyDataSetChanged()
                } else {
                    hc.esResponsable = false
                }
            }

            cbMascota.setOnCheckedChangeListener { _, isChecked ->
                hc.traeMascota = isChecked
            }

            holder.llHuespedesAsignados.addView(view)
        }

        // Búsqueda de huésped por documento para asignar libre
        holder.etBuscarHuesped.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val doc = s.toString().trim()
                val encontrado = huespedesReserva.find { it.numeroDocumento == doc }
                if (encontrado != null) {
                    // asignar al primer hueco libre
                    val libre = habitaciones[position].huespedesCheckIn.firstOrNull { it.huesped.id == 0 }
                    if (libre != null) libre.huesped = encontrado
                    notifyDataSetChanged()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
