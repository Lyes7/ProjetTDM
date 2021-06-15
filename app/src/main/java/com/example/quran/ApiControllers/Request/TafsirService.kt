package com.example.quran.ApiControllers.Request

import com.example.quran.ApiControllers.Response.AyaIndexResponse
import com.example.quran.ApiControllers.Response.AyaResponse
import com.example.quran.ApiControllers.Response.TafsirResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TafsirService {

    @GET("tafseer/{id}/{sura}/{aya}")
    fun getTafsirByMofasirId(@Path("id") Id:Int, @Path("sura") sura:Int, @Path("aya") aya:Int): Call<TafsirResponse>

}

