package com.example.tranquidescanso

data class HuespedItem(
    val id: Int,
    val nombre: String,
    val documento: String,      // antes "identificacion"
    val telefono: String,
    val email: String,
    val tipoDocumento: String
)
