package com.example.tranquidescanso.model

import java.io.Serializable

data class Habitacion(
    var id: Int = 0,
    var numero: String = "",
    var tipo: String = "",
    var capacidad: Int = 1,
    var hotelId: Int = 0,
    var hotelNombre: String = "",
    var estado: String = "Disponible",
    var descripcion: String = "",
    var huespedesCheckIn: MutableList<HuespedCheckIn> = mutableListOf() // <-- agregar esto
) : Serializable
