package com.example.tranquidescanso.model

data class HabitacionItem(
    var id: String = "",
    var nombre: String = "",
    var tipo: String = "",
    var capacidad: Int = 0,
    var precio: Double = 0.0,
    var estado: String = "",
    var descripcion: String = "",
    var servicios: List<String> = emptyList(),
    var disponibilidad: List<String> = emptyList()   // ⭐ ESTA FALTABA
)
