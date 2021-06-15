package com.example.quran.ApiControllers.Request

import retrofit2.Call
import retrofit2.http.GET


interface AyaService {
    @GET("")
    fun getAya(): Call<>
}