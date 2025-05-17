package com.example.network.dto

data class CharacterPageDto(
    val info: InfoDto,
    val results: List<CharacterDto>
)
data class InfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
