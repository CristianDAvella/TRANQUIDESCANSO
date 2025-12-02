package com.example.tranquidescanso.network

import com.example.tranquidescanso.model.Huesped
import retrofit2.Response
import retrofit2.http.*

interface HuespedService {

    @GET("huespedes")
    suspend fun obtenerHuespedes(): Response<List<Huesped>>

    @POST("huespedes")
    suspend fun crearHuesped(@Body huesped: Huesped): Response<Huesped>

    @PUT("huespedes/{id}")
    suspend fun actualizarHuesped(
        @Path("id") id: Int,
        @Body huesped: Huesped
    ): Response<Huesped>

    @DELETE("huespedes/{id}")
    suspend fun eliminarHuesped(@Path("id") id: Int): Response<Unit>
}
