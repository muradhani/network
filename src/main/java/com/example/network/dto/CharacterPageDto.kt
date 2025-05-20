package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterPageDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)
@Serializable
data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
