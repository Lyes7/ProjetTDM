package com.example.quran.ApiControllers.Response

import com.example.quran.ApiControllers.ApiModel.AyaApiIndex

data class TafsirResponse(
    val tafseer_id: Int,
    val tafseer_name: String,
    val text:String )
