package com.example.itunesk.model.network

import com.example.itunesk.BuildConfig
import com.example.itunesk.model.ItunesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {

    @GET("search")
    suspend fun getTermsByName(
        @Query("term")
        inputName: String
    ): ItunesResponse

    companion object{
        fun getApi(): ItunesApi{
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ItunesApi::class.java)
        }
    }
}