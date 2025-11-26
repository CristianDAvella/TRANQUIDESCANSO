package com.example.tranquidescanso

data class ReservaItem(
    val id: Int,
    val huesped: String,
    val agencia: String?, // null si no tiene
    val fechaInicio: String,
    val fechaFin: String,
    val habitaciones: Int
)
