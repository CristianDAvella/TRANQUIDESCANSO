package com.example.tranquidescanso.model

import java.io.Serializable

data class Reserva(
    var id: Int = 0,
    var huesped: Huesped,
    var hotel: String,
    // ahora es lista de habitaciones
    var habitaciones: MutableList<Habitacion> = mutableListOf(),
    // (mantengo agencia opcional)

    var agencia: String? = null,
    var fechaReserva: String = "",
    var fechaInicio: String = "",
    var fechaFin: String = "",
    var vencimiento: String = "",
    var cantidadPersonas: Int = 1,
    var anticipoPagado: Boolean = false,
    var estado: String = "Pendiente"
) : Serializable {
    // helper: compatibilidad rápida si otras pantallas aún esperan `habitacion` singular
    fun primeraHabitacion(): Habitacion? = if (habitaciones.isNotEmpty()) habitaciones[0] else null
}