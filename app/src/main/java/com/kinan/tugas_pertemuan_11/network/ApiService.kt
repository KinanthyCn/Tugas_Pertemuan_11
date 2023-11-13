package com.kinan.tugas_pertemuan_11.network

import com.kinan.tugas_pertemuan_11.model.Bebek
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("random")
    fun getRandomDuck() : Call<Bebek>

}
