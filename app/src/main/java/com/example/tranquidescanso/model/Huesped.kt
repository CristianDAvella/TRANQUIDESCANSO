package com.example.tranquidescanso.model

import java.io.Serializable

data class Huesped(
    var id: Int,
    var nombre: String,
    var tipoDocumento: String,
    var numeroDocumento: String,
    var telefonos: MutableList<String>,
    var correo: String,
    var tieneMascota: Boolean = false // opcional si quieres registrar mascotas
) : Serializable {
    // Propiedad calculada: es menor si el tipo de documento es "TI" o "RC"
    val esMenor: Boolean
        get() = tipoDocumento.uppercase() == "TI" || tipoDocumento.uppercase() == "RC"
}
