package com.example.tranquidescanso.model

data class Huesped(
    val id: Int,
    val nombre: String,
    val identificacion: String,
    val telefono: String,
    val email: String
)

data class Agencia(
    val id: Int,
    val nombre: String,
    val telefono: String
)

data class TipoHabitacion(
    val id: Int,
    val nombre: String
)
