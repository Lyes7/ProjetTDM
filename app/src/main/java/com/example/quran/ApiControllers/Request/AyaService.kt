package com.example.quran.ApiControllers.Request

import com.example.quran.ApiControllers.Response.AyaIndexResponse
import com.example.quran.ApiControllers.Response.AyaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface AyaService {
    @GET("aya")
    fun getAya(@Query("index") index:Int ): Call<AyaResponse>

   @GET("sura")
   fun getAyaIndex(@Query("index") sura:Int, @Query("start") aya:Int, @Query("limit") limit:Int): Call<AyaIndexResponse>
}