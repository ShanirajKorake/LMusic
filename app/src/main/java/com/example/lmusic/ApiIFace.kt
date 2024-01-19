package com.example.lmusic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiIFace {
    @Headers("X-RapidAPI-Key: 76ead701dfmsha84bd48e1787745p15c7dajsn6da8e2d692c4",
            "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q") query: String) : Call<MData>
 }