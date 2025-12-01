package com.example.tranquidescanso.model

import java.io.Serializable

data class Habitacion(
    var id: Int = 0,
    var numero: String,
    var tipo: String,
    var capacidad: Int,
    var hotelId: Int,
    var ocupada: Boolean = false,
    var descripcion: String,
    var hotelNombre: String = ""
) : Serializable
