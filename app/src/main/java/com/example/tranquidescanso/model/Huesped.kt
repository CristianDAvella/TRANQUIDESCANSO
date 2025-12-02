package com.example.tranquidescanso.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Huesped(
    var id: Int = 0,

    @SerializedName("nombre")
    var nombre: String,

    @SerializedName("tipo_id")
    var tipoDocumento: String,

    @SerializedName("numero_id")
    var numeroDocumento: String,

    @SerializedName("telefonos")
    var telefonos: MutableList<String>,

    @SerializedName("direccion")
    var correo: String,

    var tieneMascota: Boolean = false
) : Serializable {

    // Es menor si el documento es Tarjeta de Identidad (TI) o Registro Civil (RC)
    val esMenor: Boolean
        get() = tipoDocumento.uppercase() == "TI" || tipoDocumento.uppercase() == "RC"
}
