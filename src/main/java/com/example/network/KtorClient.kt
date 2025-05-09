package com.example.network

import android.util.Log
import com.example.network.dto.CharacterDto
import com.example.network.dto.EpisodeDto
import com.example.network.dto.EpisodesDto
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
    suspend fun getCharacter(id: Int): CharacterDto? = safeApiCall("getCharacter"){
        val response = client.get("character/$id")
        return if (response.status == HttpStatusCode.OK) {
            response.body<CharacterDto>()
        } else {
            null
        }
    }
    suspend fun getEpisodes(episodeIds:String): List<EpisodeDto>? = safeApiCall("getEpisodes") {
        val response = client.get("episode/$episodeIds")
        return if (response.status == HttpStatusCode.OK) {
            response.body<List<EpisodeDto>>()
        } else {
            null
        }
    }


    inline fun <T> safeApiCall(tag:String = "ApiCall",block:() -> T): T?{
        return try {
            block()
        } catch (e : Exception){
            Log.e(tag,"Network error: ${e.message}")
            null
        }
    }
}