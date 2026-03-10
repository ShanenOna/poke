package com.gabriel.pokems

import com.gabriel.pokems.model.CatchEvent
import com.gabriel.pokems.model.CatchResult
import com.gabriel.pokems.model.MovementEvent
import com.gabriel.pokems.model.TriggerEvent
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit interface used by the Android client to communicate with the
 * Spring Boot backend.  The server is responsible for producing the
 * corresponding Kafka messages, so the mobile code doesn't need to know
 * anything about Kafka directly.
 */
interface ApiService {
    @POST("api/movement")
    suspend fun sendMovement(@Body event: MovementEvent): Response<Unit>

    @POST("api/trigger")
    suspend fun sendTrigger(@Body event: TriggerEvent): Response<Unit>

    @POST("api/catch")
    suspend fun sendCatch(@Body event: CatchEvent): Response<CatchResult>
}