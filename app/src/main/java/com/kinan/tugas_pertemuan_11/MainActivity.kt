package com.kinan.tugas_pertemuan_11

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kinan.tugas_pertemuan_11.databinding.ActivityMainBinding
import com.kinan.tugas_pertemuan_11.model.Bebek
import com.kinan.tugas_pertemuan_11.network.ApiClient
import com.kinan.tugas_pertemuan_11.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar_ku))

        val client = ApiClient.getInstance()



        with(binding){
            btnShuffle.setOnClickListener(){
                shuffle(client)
            }
        }





    }

    fun shuffle(client : ApiService){
        val response : Call<Bebek> = client.getRandomDuck()
        response.enqueue(object : Callback<Bebek> {
            override fun onResponse(call: Call<Bebek>, response: Response<Bebek>) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        setImage(this@MainActivity, data.imgUrl)

                        Toast.makeText(this@MainActivity, "yeay, kamu berhasil liat bebek", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "huhuhu, bebeknya udah mati", Toast.LENGTH_SHORT).show()
                    }

                }
                else if (response.code() == 401) {
                    Toast.makeText(this@MainActivity, "sorry, kamu belum beruntung", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this@MainActivity, "mau gimana lagi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Bebek>, t: Throwable) {
                Toast.makeText(this@MainActivity, "maaf, bebeknya malu", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun setImage(context : Context, url : String) {
        Glide.with(context).load(url).centerCrop().into(binding.imageDuck)
    }
}