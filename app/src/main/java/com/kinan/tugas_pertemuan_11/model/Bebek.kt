package com.kinan.tugas_pertemuan_11.model

import com.google.gson.annotations.SerializedName

data class Bebek(
    @SerializedName("message")
    val message: String,

    @SerializedName("url")
    val imgUrl: String
)
