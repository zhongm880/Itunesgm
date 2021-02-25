package com.example.itunesk.model

import com.example.itunesk.model.network.ItunesApi

class RepositoryImpl: Repository {

    override suspend fun getArtist(inputName: String): ItunesResponse {
        return ItunesApi.getApi().getTermsByName(inputName)
    }
}