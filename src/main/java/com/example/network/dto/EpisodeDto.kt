package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)

@Serializable
data class EpisodesDto(
    val episode :List<EpisodeDto>
)