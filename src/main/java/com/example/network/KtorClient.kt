package com.example.network

import com.example.network.dto.CharacterDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.cio.Response
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject


class KtorClient @Inject constructor() {
    private val client = HttpClient(Android){
        defaultRequest { url("https://rickandmortyapi.com/api/") }
        install(Logging){ logger = Logger.SIMPLE }
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun getCharacter(id: Int): CharacterDto? {
        val response = client.get("character/$id")
        return if (response.status == HttpStatusCode.OK) {
            response.body<CharacterDto>() // Convert response body to CharacterDto
        } else {
            null // Handle error case
        }
    }
}