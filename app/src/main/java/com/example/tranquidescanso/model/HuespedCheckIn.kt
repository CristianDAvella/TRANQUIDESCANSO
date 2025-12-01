package com.example.tranquidescanso.model

import java.io.Serializable

data class HuespedCheckIn(
    var huesped: Huesped,
    var esResponsable: Boolean = false,
    var traeMascota: Boolean = false
) : Serializable
