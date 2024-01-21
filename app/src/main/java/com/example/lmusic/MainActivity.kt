package com.example.lmusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var myRCV : RecyclerView
    lateinit var myAdapter : DAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRCV = findViewById(R.id.RCV)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiIFace::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")
        retrofitData.enqueue(object : Callback<MData?> {
            override fun onResponse(call: Call<MData?>, response: Response<MData?>) {
                val dataList = response.body()?.data!!
//                val text1 : TextView = findViewById(R.id.txt1)
//                text1.text = dataList.toString()

                myAdapter = DAdapter(this@MainActivity, dataList)
                myRCV.adapter=myAdapter
                myRCV.layoutManager=LinearLayoutManager(this@MainActivity)
                Log.d("TAG onResponse", "onResponse: " +response.body())

            }

            override fun onFailure(call: Call<MData?>, t: Throwable) {
                Log.d("TAG onFailure", "onFailure: " + t.message)
            }
        })
    }
}

