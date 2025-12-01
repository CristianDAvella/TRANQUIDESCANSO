package com.example.tranquidescanso.model

import java.io.Serializable

data class Hotel(
    var nombre: String,
    var direccion: String,
    var telefonos: MutableList<String>,
    var fecha: String,
    var categoria: String,
    var historialCategorias: MutableList<String> = mutableListOf()
) : Serializable
