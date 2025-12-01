package com.example.tranquidescanso.model

data class Reserva(
    val id: Int,
    val huesped: Huesped,
    var hotel: String,
    val habitaciones: MutableList<Habitacion>,
    val agencia: String?,
    var fechaReserva: String,
    var fechaInicio: String,
    var fechaFin: String,
    var vencimiento: String,
    var cantidadPersonas: Int,
    var anticipoPagado: Boolean,
    var estado: String,
    val serviciosAsignados: MutableList<Servicio> = mutableListOf() // <-- para tus servicios
)
