package com.example.tranquidescanso.model

import java.io.Serializable

data class Huesped(
    var id: Int = 0,
    var nombre: String,
    var tipoDocumento: String,
    var numeroDocumento: String,
    var telefonos: MutableList<String>,
    var correo: String
) : Serializable
