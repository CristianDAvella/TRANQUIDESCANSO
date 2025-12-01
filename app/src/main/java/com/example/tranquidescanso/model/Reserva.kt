package com.example.tranquidescanso.model

import java.io.Serializable

data class Reserva(
    var id: Int = 0,
    var huesped: Huesped,
    var hotel: String,
    var habitacion: Habitacion,
    var agencia: String?,
    var fechaReserva: String,
    var fechaInicio: String,
    var fechaFin: String,
    var vencimiento: String,
    var cantidadPersonas: Int,
    var anticipoPagado: Boolean,
    var estado: String
) : Serializable
