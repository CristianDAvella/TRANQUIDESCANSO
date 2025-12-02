package com.example.tranquidescanso.repository

import com.example.tranquidescanso.model.Huesped
import com.example.tranquidescanso.network.HuespedService
import com.example.tranquidescanso.network.RetrofitClient

class HuespedRepository {

    private val api = RetrofitClient.instance.create(HuespedService::class.java)

    suspend fun obtenerHuespedes(): List<Huesped>? {
        val response = api.obtenerHuespedes()
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun crearHuesped(huesped: Huesped): Huesped? {
        val response = api.crearHuesped(huesped)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun actualizarHuesped(id: Int, huesped: Huesped): Huesped? {
        val response = api.actualizarHuesped(id, huesped)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun eliminarHuesped(id: Int): Boolean {
        val response = api.eliminarHuesped(id)
        return response.isSuccessful
    }
}
