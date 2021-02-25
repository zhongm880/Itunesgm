package com.example.itunesk.model

data class ItunesResponse(
    val results: List<ItunesData>
)

data class ItunesData(
    val artistName: String,
    val trackName: String,
    val trackPrice: Double,
    val releaseDate: String,
    val primaryGenreName: String
)