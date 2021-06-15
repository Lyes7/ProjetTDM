package com.example.quran.ApiControllers.ApiModel

data class AyaApi(
    val index:Int,
    val sura: Int,
    val aya: Int,
    val simple: String,
    val translate: AyaTranslate
) {
}