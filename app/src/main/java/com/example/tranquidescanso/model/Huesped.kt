package com.example.tranquidescanso.model

import java.io.Serializable

data class Huesped(
    var id: Int,
    var nombre: String,
    var tipoDocumento: String,
    var numeroDocumento: String,
    var telefonos: MutableList<String>,
    var correo: String,
    var tieneMascota: Boolean = false
) : Serializable {

    // Es menor si el documento es Tarjeta de Identidad o Registro Civil
    val esMenor: Boolean
        get() = tipoDocumento.uppercase() == "TI" || tipoDocumento.uppercase() == "RC"
}
