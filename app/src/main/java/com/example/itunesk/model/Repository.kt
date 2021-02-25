package com.example.itunesk.model

interface Repository {
    suspend fun getArtist(inputName: String): ItunesResponse
}